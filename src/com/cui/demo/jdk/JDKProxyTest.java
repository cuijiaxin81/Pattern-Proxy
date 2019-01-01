package com.cui.demo.jdk;

import com.cui.demo.staticproxy.Person;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;


public class JDKProxyTest {
    public static void main(String[] args) throws IOException {
        Person obj = (Person) new JDK58().getInstance(new XieMu());
        //obj.findLove();
        obj.zufangzi();


        byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy0",new Class[]{Person.class});
        FileOutputStream fos = new FileOutputStream("e://$Proxy0.class");
        fos.write(bytes);
    }

}

