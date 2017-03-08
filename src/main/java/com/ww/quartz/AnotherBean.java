package com.ww.quartz;

import org.springframework.stereotype.Component;

/**
 * Created by ww on 17/3/8.
 */
@Component("anotherBean")
public class AnotherBean {
    public void printMessage() {
        System.out.println("I am AnotherBean. ");
    }
}
