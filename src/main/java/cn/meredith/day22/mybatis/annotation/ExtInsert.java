package cn.meredith.day22.mybatis.annotation;

import java.lang.annotation.*;

/**
 * 自定义插入注解
 *
 * @author Meredith
 * @date
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExtInsert {

    String value();
}
