package org.dididu.controller;

import org.dididu.domain.ClientState;
import org.dididu.repository.OmniRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class OmniController {

    private static Logger log = LoggerFactory.getLogger(OmniController.class);

    @Autowired
    private OmniRepository repository;

    @RequestMapping("/{clientId}")
    public String greeting(@PathVariable(value="clientId") String clientId, Model model) {
        model.addAttribute("clientId", clientId);

        ClientState clientState = null;

        try {
            clientState = repository.findOne(clientId);
        } catch (Exception e) {
            log.error("Error accessing the DB", e);
        }

        model.addAttribute("body", clientState != null ? clientState.body : "");

        return "omni";
    }
}
