package org.dididu.controller;

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

    @RequestMapping(value = "/{clientId}", method = RequestMethod.POST,
            consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void submit(@PathVariable(value="clientId") String clientId,
                             @RequestBody PayloadDefinition payload) {
        try {
            String decodedHtml = URLDecoder.decode(payload.payloadHtml, "utf-8");
            PayloadMessageContainer payloadMessageContainer =
                    new PayloadMessageContainer(payload.selector, decodedHtml);

            template.convertAndSend("/submit-topic/" + clientId, payloadMessageContainer);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


}
