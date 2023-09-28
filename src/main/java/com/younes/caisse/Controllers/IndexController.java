package com.younes.caisse.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "dashboard/index"; // Return the template name without the ".html" extension
    }
}