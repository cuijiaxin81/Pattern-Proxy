package com.cui.demo.jdk;

import com.cui.demo.staticproxy.Person;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKMeipo implements InvocationHandler {

    //被代理对象，把引用保存下来
    private Person target;

    public Object getInstance(XieMu xieMu) {
        this.target = xieMu;

        Class<?> clazz = target.getClass();

        //用来生成一个新的对象（字节码重组来实现）
        return Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("我是媒婆：我要给你找对象，现在已经拿到需求");
        System.out.println("开始物色");

        method.invoke(this.target,args);

        System.out.println("如果合适，就准备办事");

        return  null;
    }
}
