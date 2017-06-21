package com.zk;

/**
 * Created by zk on 17-6-21.
 */
public class CglibMain {

    public static void main(String[] args) {
        Person person = ProxyFactory.getPersonInstance();
        System.out.println(person.sayHello("孙悟空"));
        person.eat("西瓜");
        System.out.println(person.getClass());
    }

}
