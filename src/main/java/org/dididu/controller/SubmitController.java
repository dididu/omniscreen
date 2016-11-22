package org.dididu.controller;

import org.dididu.domain.LayoutMessageContainer;
import org.dididu.domain.PayloadDefinition;
import org.dididu.domain.PayloadMessageContainer;
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

    @RequestMapping(value = "/widget", method = RequestMethod.POST,
            consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void submitUpdate(@RequestBody PayloadDefinition payload) {
        try {
            String decodedHtml = URLDecoder.decode(payload.payloadHtml, "utf-8");
            PayloadMessageContainer payloadMessageContainer =
                    new PayloadMessageContainer(payload.id, decodedHtml);

            template.convertAndSend("/topic/widgets", payloadMessageContainer);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/layout", method = RequestMethod.POST,
            consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void submitLayout(@RequestBody String layoutHtml) {
        try {
            String decodedLayoutHtml = URLDecoder.decode(layoutHtml, "utf-8");
            LayoutMessageContainer layoutMessageContainer = new LayoutMessageContainer(decodedLayoutHtml);

            template.convertAndSend("/topic/widgets", layoutMessageContainer);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
