package ru.rinat.users.ntest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Calculate {
    private final List<Checker> checkerList;

    public Integer calculate() {
        int result = 0;
        for (Checker c : checkerList) {
             c.check();
             result = c.getType();
        }
        return result;
    }
}
