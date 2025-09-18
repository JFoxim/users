package ru.rinat.users.ntest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheckerOne implements Checker {

    @Override
    public boolean check() {
        System.out.println("CheckerOne");
        return false;
    }

    @Override
    public Integer getType() {
        return 1;
    }
}
