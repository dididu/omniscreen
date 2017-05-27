package org.dididu.controller;

import org.dididu.service.TemplateService;
import org.dididu.domain.TemplateData;
import org.dididu.domain.TemplateMessageContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
class SubmitDataController {

    private final SimpMessagingTemplate messagingTemplate;
    private final TemplateService templateService;

    @Autowired
    public SubmitDataController(SimpMessagingTemplate messagingTemplate, TemplateService templateService) {
        this.messagingTemplate = messagingTemplate;
        this.templateService = templateService;
    }

    @RequestMapping(value = "/{userId}/data", method = RequestMethod.POST,
            consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void submit(@PathVariable("userId") String userId,
                             @RequestBody TemplateData templateData) {

        templateService.saveDataForUser(userId, templateData);
        String renderedTemplate = templateService.renderTemplateForUser(userId);

        TemplateMessageContainer templateMessageContainer = new TemplateMessageContainer(renderedTemplate);

        messagingTemplate.convertAndSend("/submit-topic/" + userId, templateMessageContainer);
    }
}
