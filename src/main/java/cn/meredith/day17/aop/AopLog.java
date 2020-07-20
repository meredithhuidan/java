package cn.meredith.day17.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * AOP切面类
 */
@Component
@Aspect
public class AopLog {

    //AOP编程里面有几个通知
    //前置通知、后置通知、运行通知、异常通知、环绕通知
    @Before("execution(* cn.meredith.day17.service.UserService.add())")
    public void before(){
        System.out.println("前置通知 在方法之前执行...");
    }

    @After("execution(* cn.meredith.day17.service.UserService.add())")
    public void after(){
        System.out.println("后置通知 在方法之后执行...");
    }

    @AfterReturning("execution(* cn.meredith.day17.service.UserService.add())")
    public void returning(){
        System.out.println("运行通知");
    }

    //异常后才通知
    @AfterThrowing("execution(* cn.meredith.day17.service.UserService.add())")
    public void afterThrowing(){
        System.out.println("异常通知");
    }

    //如果调用方法抛出异常，不会执行后面代码
    //环绕通知在方法之前和之后处理事情
    @Around("execution(* cn.meredith.day17.service.UserService.add())")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        System.out.println("环绕通知 调用方法之前执行");
        proceedingJoinPoint.proceed();  //代理调用方法 如果调用方法抛出异常，不会执行后面代码
        System.out.println("环绕通知 调用方法之后执行");
    }
}
