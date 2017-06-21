package com.zk;

import net.sf.cglib.proxy.Enhancer;

/**
 * Created by zk on 17-6-21.
 */
public class ProxyFactory {

    public static Person getPersonInstance() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Person.class);
        enhancer.setCallback(new AroundAdvice());
        return (Person)enhancer.create();
    }

}
