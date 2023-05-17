package sezergemtsov.profiles.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import sezergemtsov.profiles.configs.H2Config;
import sezergemtsov.profiles.models.Message;
import sezergemtsov.profiles.services.Service;

import java.util.List;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class Controller {

    private final Service service;
    private H2Config config;

    @GetMapping("/")
    public String hello(@Value("${my.value}") String value) {
        return value;
    }

    @GetMapping("db")
    public String test() {
        return config.getUrl();
    }

    @GetMapping("/messages")
    public List<Message> getMessage() {
        return service.getMessage();
    }

    @PostMapping("/save")
    public void saveMessage(@RequestParam("message") String message) {
        service.saveMessage(message);
    }

}
