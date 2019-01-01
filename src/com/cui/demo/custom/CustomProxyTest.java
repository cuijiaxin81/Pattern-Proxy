package com.cui.demo.custom;

import com.cui.demo.jdk.XieMu;
import com.cui.demo.staticproxy.Person;

import java.io.IOException;

public class CustomProxyTest {

    public static void main(String[] args) throws IOException {
        Person obj = (Person) new CustomMeipo().getInstance(new XieMu());
        obj.findLove();



    }

}
