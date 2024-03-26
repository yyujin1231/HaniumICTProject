package com.example.packpagedosirak.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @GetMapping("/")
    @ResponseBody
    public String mainAPI() {

        return "main route";
    }

    @GetMapping("/my")
    @ResponseBody
    public String myAPI() {

        return "my route";
    }
}
