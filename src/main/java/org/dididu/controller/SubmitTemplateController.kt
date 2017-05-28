package org.dididu.controller

import org.dididu.service.TemplateService
import org.dididu.domain.TemplateDefinition
import org.dididu.domain.TemplateMessageContainer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.web.bind.annotation.*

@RestController
internal class SubmitTemplateController @Autowired
constructor(private val messagingTemplate: SimpMessagingTemplate, private val templateService: TemplateService) {

    @RequestMapping(value = "/{userId}/template", method = arrayOf(RequestMethod.POST), consumes = arrayOf("application/json"))
    @ResponseStatus(HttpStatus.OK)
    fun submit(@PathVariable("userId") userId: String,
               @RequestBody templateDefinition: TemplateDefinition) {

        templateService.saveTemplateForUser(userId, templateDefinition)
        val templateMessageContainer = TemplateMessageContainer(templateService.renderTemplateForUser(userId))
        messagingTemplate.convertAndSend("/submit-topic/" + userId, templateMessageContainer)
    }
}
