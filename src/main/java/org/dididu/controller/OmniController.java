package org.dididu.controller;

import org.dididu.domain.ClientState;
import org.dididu.repository.OmniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class OmniController {

    @Autowired
    private OmniRepository repository;

    @RequestMapping("/omni/{clientId}")
    public String greeting(@PathVariable(value="clientId", required=false) String clientId, Model model) {
        model.addAttribute("clientId", clientId);

        ClientState clientState = repository.findOne(clientId);
        model.addAttribute("body", clientState != null ? clientState.body : "");

        return "omni";
    }
}
