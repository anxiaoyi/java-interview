package com.zk;

import javassist.*;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.annotation.MemberValue;
import javassist.bytecode.annotation.StringMemberValue;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.Set;

/**
 * Created by zk on 17-6-21.
 */
public class JavaAssistMain {


    public static void main(String[] args) {
        ClassPool pool = new ClassPool(true);
        pool.importPackage("com.zk");
        pool.importPackage("com.zk.fruit");
        CtClass cls = pool.makeClass("com.zk.GeneratedClass");

        try {
            // 添加默认构造器
            cls.addConstructor(CtNewConstructor.defaultConstructor(cls));
            // 添加字段
            CtField ctField = CtField.make("private String color;", cls);
            cls.addField(ctField);
            // 为字段添加一个 @A(name="Poppet") 注解
            ClassFile classFile = cls.getClassFile();
            javassist.bytecode.annotation.Annotation annotation =
                    new javassist.bytecode.annotation.Annotation("com.zk.A", classFile.getConstPool());
            MemberValue memberValue = javassist.bytecode.annotation.Annotation.createMemberValue(classFile.getConstPool(), pool.get("java.lang.String"));
            ((StringMemberValue)memberValue).setValue("Poppet");
            annotation.addMemberValue("name", memberValue);
            AnnotationsAttribute annotationsAttribute = new AnnotationsAttribute(classFile.getConstPool(), AnnotationsAttribute.visibleTag);
            annotationsAttribute.addAnnotation(annotation);
            ctField.getFieldInfo().addAttribute(annotationsAttribute);

            // 添加方法
            cls.addMethod(CtNewMethod.make(CtClass.voidType, "hello", null, null, "System.out.println(\"Hello World\");", cls));
            // 添加接口
            cls.addInterface(pool.getCtClass("com.zk.fruit.IFruit"));
            Class<?> clazz = cls.toClass();

            try {
                cls.writeFile(System.getProperty("user.dir") + "/src/main/java");
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                Object obj = clazz.newInstance();

                // 调用方法
                Method method = clazz.getMethod("hello");
                method.invoke(obj);

                // 打印字段
                Field field = clazz.getDeclaredField("color");
                System.out.println(field.getName());

                // 打印字段是否声明了注解
                Annotation annotation1 = field.getAnnotation(com.zk.A.class);
                System.out.println(annotation1.toString());

                // 是否实现了 IFruit 接口
                Class<?> fruitInterface = clazz.getInterfaces()[0];
                System.out.println(fruitInterface.getName());
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

}
