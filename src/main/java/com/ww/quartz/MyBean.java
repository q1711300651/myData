package com.ww.quartz;

import org.springframework.stereotype.Component;

/**
 * Created by ww on 17/3/8.
 */
@Component("myBean")
public class MyBean {
    public void printMessage() {
        System.out.println("I am MyBean. I am called by MethodInvokingJobDetailFactoryBean using SimpleTriggerFactoryBean");
    }
}
