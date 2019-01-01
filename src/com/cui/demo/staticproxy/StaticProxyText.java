package com.cui.demo.staticproxy;

public class StaticProxyText {

    public static void main(String[] args) {
        Son son = new Son();
        Father father = new Father(son);
        //只能帮儿子找对象
        father.findLove();
    }
}
