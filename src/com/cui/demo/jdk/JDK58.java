package com.cui.demo.jdk;

import com.cui.demo.staticproxy.Person;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDK58 implements InvocationHandler {

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
        System.out.println("我是58：租房租找房子找工作");
        System.out.println("开始物色");

        method.invoke(this.target,args);

        System.out.println("上58！");

        return  null;
    }
}
