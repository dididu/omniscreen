package org.dididu.controller;

import org.dididu.domain.ClientState;
import org.dididu.domain.Result;
import org.dididu.repository.OmniRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class ResultController {

    private static Logger log = LoggerFactory.getLogger(ResultController.class);

    @Autowired
    private OmniRepository repository;

    @MessageMapping("/result")
    public void receiveResult(Result result) {
        log.trace("Result received: {}", result);

        ClientState clientState = new ClientState();
        clientState.clientId = result.clientId;
        clientState.body = result.body;

        try {
            repository.save(clientState);
        } catch (Exception e) {
            log.error("Error accessing the DB", e);
        }
    }
}
