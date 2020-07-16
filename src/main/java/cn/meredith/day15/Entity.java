package cn.meredith.day15;

/**
 * 字节码技术
 */
public class Entity {

    private String name;

    private Long id;

    public void add(){

    }

    //可以使用字节码技术对类的基本信息做操作，新增属性或方法、类，修改属性或者方法，删除属性或者方法
    //动态代理：不需要生成代理类对象，通过字节码技术虚拟生成 cglib
    //使用字节码技术，实现类似cglib框架，实现动态代理效果
    //在线执行java代码 javaCompiler 动态编译
}
