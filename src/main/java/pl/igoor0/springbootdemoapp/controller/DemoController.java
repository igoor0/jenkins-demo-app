package pl.igoor0.springbootdemoapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.igoor0.springbootdemoapp.service.DemoService;

@RestController
@RequestMapping("/api/v1/demo")
@RequiredArgsConstructor
public class DemoController {

    private final DemoService demoService;

    @GetMapping
    public String getDemo() {
        return demoService.getMessage();
    }
}
