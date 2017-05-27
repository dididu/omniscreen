package org.dididu.controller;

import org.dididu.service.TemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OmniController {

    private static Logger log = LoggerFactory.getLogger(OmniController.class);

    @Autowired
    private TemplateService templateService;

    @RequestMapping("/display/{userId}")
    public String greeting(@PathVariable("userId") String userId, Model model) {
        model.addAttribute("clientId", userId);

        String renderedTemplate = templateService.renderTemplateForUser(userId);
        model.addAttribute("body", renderedTemplate);

        return "omni";
    }
}
