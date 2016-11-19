package org.dididu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@RestController
public class SubmitController {

    @Autowired
    private SimpMessagingTemplate template;

    @RequestMapping(value = "/widget", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void submitWidget(@RequestBody String widget) {
        try {
            String decodedHtml = URLDecoder.decode(widget, "utf-8");
            template.convertAndSend("/topic/widgets", decodedHtml);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
