package org.dididu.controller

import org.dididu.service.TemplateService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
internal class OmniController @Autowired
constructor(private val templateService: TemplateService) {
    @RequestMapping("/display/{userId}")
    fun greeting(@PathVariable("userId") userId: String, model: Model): String {
        model.addAttribute("clientId", userId)
        model.addAttribute("body", templateService.renderTemplateForUser(userId))

        return "omni"
    }
}
