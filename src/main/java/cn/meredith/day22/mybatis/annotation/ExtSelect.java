package cn.meredith.day22.mybatis.annotation;

import java.lang.annotation.*;

/**
 * 查询
 *
 * @author Meredith
 * @date
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExtSelect {

    String value();
}
