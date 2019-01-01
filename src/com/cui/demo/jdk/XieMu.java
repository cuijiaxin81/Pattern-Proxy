package com.cui.demo.jdk;

import com.cui.demo.staticproxy.Person;

public class XieMu implements Person {

    public void findLove() {
        System.out.println("高富帅");
        System.out.println("身高180");
    }

    @Override
    public void zufangzi() {
        System.out.println("租房子");
        System.out.println("找工作");
    }

}
