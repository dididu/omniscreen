package org.dididu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class SubmitController {

    @Autowired
    private SimpMessagingTemplate template;

    @RequestMapping(value = "/widget", method = RequestMethod.POST)
    public void submitWidget(@RequestBody String widget) {
        template.convertAndSend("/topic/widgets", widget);
    }
}
