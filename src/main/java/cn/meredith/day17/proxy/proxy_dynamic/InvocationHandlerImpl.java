package cn.meredith.day17.proxy.proxy_dynamic;

import cn.meredith.day17.UserService;
import cn.meredith.day17.UserServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 * 每次生成动态代理对象时，实现InvocationHandler接口
 * jdk动态代理
 *
 * @author Meredith
 * @date
 */
public class InvocationHandlerImpl implements InvocationHandler {

    private Object target;

    public InvocationHandlerImpl(Object target){
        this.target=target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result=null;
        System.out.println("使用jdk动态代理--开始事务");
        result=method.invoke(target,args);
        System.out.println("使用jdk动态代理--提交事务");
        return result;
    }

    public static void main(String[] args) {

        //被代理的对象
        UserService userService =new UserServiceImpl();

        InvocationHandlerImpl invocationHandler=new InvocationHandlerImpl(userService);
        ClassLoader loader=userService.getClass().getClassLoader();
        Class[] interfaces=userService.getClass().getInterfaces();
        //主要装载器、一组接口、调用处理动态代理实例
        UserService newProxyInstance= (UserService) Proxy.newProxyInstance(loader,interfaces,invocationHandler);
        newProxyInstance.add();
    }
}
