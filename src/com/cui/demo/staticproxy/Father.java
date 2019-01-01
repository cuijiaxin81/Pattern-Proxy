package com.cui.demo.staticproxy;

public class Father {

    private Person person;

    public Father(Person person) {
        this.person = person;
    }

    //目标对象的引用
    public void findLove() {
        System.out.println("根据要求物色");
        person.findLove();
        System.out.println("双方父母是否统一");
    }
}
