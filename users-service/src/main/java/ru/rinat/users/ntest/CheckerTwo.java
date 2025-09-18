package ru.rinat.users.ntest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheckerTwo implements Checker {

    @Override
    public boolean check() {
        System.out.println("CheckerTwo");
        return true;
    }

    @Override
    public Integer getType() {
        return 2;
    }
}
