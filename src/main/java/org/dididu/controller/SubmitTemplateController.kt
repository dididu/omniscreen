package org.dididu.controller

import org.dididu.domain.TemplateDefinition
import org.dididu.service.TemplateService
import org.dididu.domain.TemplateMessageContainer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
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
               @RequestBody templateString: String) {

        log.info("/$userId/template; template = $templateString")

        val templateDefinition = TemplateDefinition(userId, templateString)
        templateService.saveTemplate(templateDefinition)

        val templateMessageContainer = TemplateMessageContainer(templateService.renderTemplateForUser(userId))
        messagingTemplate.convertAndSend("/submit-topic/" + userId, templateMessageContainer)
    }

    companion object {
        val log : Logger = LoggerFactory.getLogger(OmniController::class.java)
    }
}
