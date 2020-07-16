package cn.meredith.day15.classes;

import javassist.*;

import java.io.IOException;

/**
 * 使用javasssit创建class文件
 *
 * @author Meredith
 * @date
 */
public class Test002 {

    public static void main(String[] args) throws CannotCompileException, NotFoundException, IOException {

        ClassPool pool=ClassPool.getDefault();
        //1、创建user类
        CtClass userClass=pool.makeClass("cn.meredith.day15.classes.User");
        //2、创建name和age属性
        CtField nameField=CtField.make("private String name;",userClass);
        CtField ageField=CtField.make("private Integer age;",userClass);
        //3、添加属性
        userClass.addField(nameField);
        userClass.addField(ageField);
        //4、创建方法
        CtMethod getNameMethod=CtMethod.make("public String getName() { return name;}",userClass);
        //5、添加方法
        userClass.addMethod(getNameMethod);
        //6、添加构造函数
        CtConstructor ctConstructor=new CtConstructor(new CtClass[]{pool.get("java.lang.String"),pool.get("java.lang.Integer")},userClass);
        ctConstructor.setBody(" { this.name=name;this.age=age; } ");
        userClass.addConstructor(ctConstructor);

        //生成class文件
        userClass.writeFile("E://Java_project//WrokSpace//thread_demo//src//main//java//cn//meredith//day15//classes");

    }
}
