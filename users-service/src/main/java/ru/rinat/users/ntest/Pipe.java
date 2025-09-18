package ru.rinat.users.ntest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Pipe {
    private final Calculate calculate;

    public void run() {
      calculate.calculate();
    }
}
