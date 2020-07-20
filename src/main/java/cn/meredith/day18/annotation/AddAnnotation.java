package cn.meredith.day18.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义注解 @interface
 *
 * @author Meredith
 * @date
 */
@Target(value = ElementType.METHOD) //设置注解权限
@Retention(value = RetentionPolicy.RUNTIME)    //用于描述注解的生命周期
public @interface AddAnnotation {

    //手写spring事务注解
    int userId() default 0;
    String userName() default "默认名称";
    String[] arrays();
}
