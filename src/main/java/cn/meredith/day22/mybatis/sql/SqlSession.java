package cn.meredith.day22.mybatis.sql;

import cn.meredith.day22.mybatis.aop.MyInvocationHandlerMybatis;

import java.lang.reflect.Proxy;

/**
 * SqlSession
 */
public class SqlSession {

    public static <T> T getMapper(Class classz) {
        return (T) Proxy.newProxyInstance(classz.getClassLoader(), new Class[]{classz}, new MyInvocationHandlerMybatis(classz));
    }
}
