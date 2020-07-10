package cn.meredith.day10.jdkproxy;

import cn.meredith.day10.proxy.IUserDao;
import cn.meredith.day10.proxy.UserDaoImp;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk动态代理
 * 每次生成动态代理类对象时，实现了InvocationHandler接口的调用处理器对象
 */
public class InvocationHandlerImpl implements InvocationHandler {

    //目标代理对象
    private Object target;

    public InvocationHandlerImpl(Object target){
        this.target=target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //开启事务
        System.out.println("动态代理开启事务");
        Object invoke=method.invoke(target,args);
        System.out.println("动态代理提交事务");
        return invoke;
    }

    public static void main(String[] args) {
         IUserDao iUserDao =new UserDaoImp();
         InvocationHandlerImpl invocationHandlerImp=new InvocationHandlerImpl(iUserDao);
         ClassLoader classLoader=iUserDao.getClass().getClassLoader();
         //获取当前实现的接口
         Class[] interfaces=iUserDao.getClass().getInterfaces();
         //调用动态代理实例
        IUserDao iUserDao1=(IUserDao) Proxy.newProxyInstance(classLoader,interfaces,invocationHandlerImp);
        iUserDao1.add();
    }
}
