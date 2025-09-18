package ru.rinat.users.ntest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Controller {
    private final Calculate calculate;

    @GetMapping("/calc")
    public String hello() {
        return calculate.calculate().toString();
    }

}
