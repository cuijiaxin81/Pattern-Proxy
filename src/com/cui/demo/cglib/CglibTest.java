package com.cui.demo.cglib;

import org.omg.CORBA.OBJ_ADAPTER;

public class CglibTest {

    public static void main(String[] args) {
        Zhangsan object =(Zhangsan) new CglibMeipo().getInstance(Zhangsan.class);
        object.findLove();
        System.out.println(object.getClass());
    }
}
