package cn.meredith.day17.aop;

import cn.meredith.day17.transaction.TransactionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * AOP整合编程事务
 * 使用环绕通知
 * 切面类 基于手动事务封装
 */
@Aspect
@Component
public class AopTransaction {

    //transactionUtils 不要设置为单例的。否则会发生线程安全问题
    @Autowired
    private TransactionUtils transactionUtils;

    @AfterThrowing("execution(* cn.meredith.day17.service.UserService.add())")
    public void afterThrowing() {
        System.out.println("回滚事务");
        //获取当前事务 直接回滚
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

    }


    //如果调用方法抛出异常，不会执行后面代码
    @Around("execution(* cn.meredith.day17.service.UserService.add())")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        System.out.println("开启事务");
        TransactionStatus transactionStatus = transactionUtils.begin();
        proceedingJoinPoint.proceed();  //代理调用方法 如果调用方法抛出异常，不会执行后面代码
        System.out.println("提交事务");
        transactionUtils.commit(transactionStatus);
    }
}
