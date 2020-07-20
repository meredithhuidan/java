package cn.meredith.day18.aop;

import cn.meredith.day18.annotation.ExtTransactional;
import cn.meredith.day18.transaction.TransactionUtils1;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.lang.reflect.Method;

/**
 * 自定义事务注解具体实现
 * 进行代码重构
 *
 * @author Meredith
 * @date
 */
@Aspect
@Component
public class AopExtTransaction {

    //一个事务的实例 针对一个事务
    @Autowired
    private TransactionUtils1 transactionUtils1;

    //使用异常通知进行回滚事务
    @AfterThrowing("execution(* cn.meredith.day18.service.*.*(..))")
    public void afterThrowing() {
        //获取当前事务 直接回滚
//        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        //判断下异常方法是否有事务注解，如果有就进行回滚
        transactionUtils1.rollback();
    }

    //环绕通知
    @Around("execution(* cn.meredith.day18.service.*.*(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {


        //1、获取代理对象的方法
        ExtTransactional extTransactional=getMethodExtTransaction(proceedingJoinPoint);
        //2、获取该方法上是否存在注解 ,如果存在事务注解，开启事务
        TransactionStatus transactionStatus=begin(extTransactional);
        //4、调用目标代理对象方法
        proceedingJoinPoint.proceed();
        //5、判断该方法上是否存在注解
        commit(transactionStatus);
    }

    private void commit(TransactionStatus transactionStatus){
        //5、判断该方法上是否存在注解
//        if (extTransactional!=null){ //通过事务判断不太好
        //通过状态判断
        if (transactionStatus!=null){
            //6、如果存在注解，提交事务
            transactionUtils1.commit(transactionStatus);
        }
    }

    private TransactionStatus begin(ExtTransactional extTransactional){
        //2、获取该方法上是否存在注解
        if (extTransactional == null) {
            return null;
        }
        //方法上加上事务注解
        //3、如果存在事务注解，开启事务
        return transactionUtils1.begin();
    }

    //获取代理对象的方法
    private ExtTransactional getMethodExtTransaction(ProceedingJoinPoint proceedingJoinPoint) throws NoSuchMethodException {
        //1、获取代理对象的方法
        //获取方法名称
        String methodName=proceedingJoinPoint.getSignature().getName();
        //获取目标对象
        Class classTarget=proceedingJoinPoint.getTarget().getClass();
        //获取目标对象类型
        Class[] par=((MethodSignature)proceedingJoinPoint.getSignature()).getParameterTypes();
        //获取目标对象方法
        Method objMethod=classTarget.getMethod(methodName,par);
        ExtTransactional extTransactional=objMethod.getDeclaredAnnotation(ExtTransactional.class);
        return extTransactional;
    }
}
