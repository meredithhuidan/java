package cn.meredith.day22.mybatis;

/**
 * mybatis 难点
 * 1、Mapper接口的方法需要和sql语句进行绑定
 *      接口不能实例化
 *      userMapper  1)使用字节码技术创建虚拟子类
 *                  2）使用匿名内部类方式
 *                  3）使用动态代理方式创建代理对象
 *                      3.1）cglib
 *                      3.2）反射
 *
 * @author Meredith
 * @date
 */
public class Test001 {
}
