package org.dididu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class OmniController {

    @RequestMapping("/omni/{clientId}")
    public String greeting(@PathVariable(value="clientId", required=false) String clientId, Model model) {
        model.addAttribute("clientId", clientId);
        return "omni";
    }
}
