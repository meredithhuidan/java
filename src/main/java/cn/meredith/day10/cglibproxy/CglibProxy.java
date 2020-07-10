package cn.meredith.day10.cglibproxy;

import cn.meredith.day10.proxy.IUserDao;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {

    private Object targetObject;

    public Object getInstance(Object target){
        this.targetObject=target;
        //操作字节码 生成虚拟子类
        Enhancer enhancer=new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib开启事务");
        Object invoke=methodProxy.invoke(targetObject,objects);
        System.out.println("cglib提交事务");
        return invoke;
    }

    /**
     * 日志 控制层打印日志 AOP技术 环绕通知 方法之前和之后进行拦截参数打印
     *
     * 1、CGLIB 没有依赖接口，CGLIB底层用的字节码技术
     * 2、jdk底层用的反射技术，
     *
     * @param args
     */
    public static void main(String[] args) {
        CglibProxy cglibProxy=new CglibProxy();
        cn.meredith.day10.cglibproxy.UserDaoImp userDaoImp=(UserDaoImp) cglibProxy.getInstance(new cn.meredith.day10.cglibproxy.UserDaoImp());
        userDaoImp.add();

        //怎么判断一个类是否实现接口？
        UserDaoImp userDaoImp1=new UserDaoImp();
        Class[] interfaces=userDaoImp1.getClass().getInterfaces();
        System.out.println(interfaces.length);
    }
}
