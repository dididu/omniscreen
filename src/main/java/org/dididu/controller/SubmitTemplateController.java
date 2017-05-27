package org.dididu.controller;

import org.dididu.service.TemplateService;
import org.dididu.domain.TemplateDefinition;
import org.dididu.domain.TemplateMessageContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class SubmitTemplateController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private TemplateService templateService;

    @RequestMapping(value = "/{userId}/template", method = RequestMethod.POST,
            consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void submit(@PathVariable("userId") String userId,
                             @RequestBody TemplateDefinition templateDefinition) {

        templateService.saveTemplateForUser(userId, templateDefinition);
        String renderedTemplate = templateService.renderTemplateForUser(userId);

        TemplateMessageContainer templateMessageContainer = new TemplateMessageContainer(renderedTemplate);

        messagingTemplate.convertAndSend("/submit-topic/" + userId, templateMessageContainer);
    }
}
