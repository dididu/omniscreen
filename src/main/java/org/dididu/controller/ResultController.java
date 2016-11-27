package org.dididu.controller;

import org.dididu.domain.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class ResultController {

    private static Logger log = LoggerFactory.getLogger(ResultController.class);

    @MessageMapping("/result")
    public void receiveResult(Result result) {
        log.trace("Result received: {}", result);
    }
}
