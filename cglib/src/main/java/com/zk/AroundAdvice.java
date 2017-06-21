package com.zk;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by zk on 17-6-21.
 */
public class AroundAdvice implements MethodInterceptor {

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("执行目标方法之前，模拟开始事务……");
        Object result = methodProxy.invokeSuper(o, new String[]{ "被改变的参数" });
        System.out.println("执行目标方法之后，模拟结束事务……");
        return result + " 新增的内容";
    }

}
