package ru.practicum;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloMessageController {
    private final HelloMessageService messageService;

    public HelloMessageController(HelloMessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public String hello() {
        return messageService.getHelloMessage();
    }
}
