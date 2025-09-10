package FeatureFlagAPI.ApiFlag.controller.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HelloWorldController {

    @GetMapping("/api/v1/flag/hello")
    public String helloWord() {
        return "Hello World!";
    }
}
