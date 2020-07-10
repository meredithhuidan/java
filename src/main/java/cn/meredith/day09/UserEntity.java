package cn.meredith.day09;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射
 *
 * @author Meredith
 * @dadte
 */
public class UserEntity extends Object {

    private String userName;

    //构造函数改为private 能创建成功吗？
    //在一个class里没有什么权限，都可以获得
    //main里面内容放另一个class,就会创建失败
    private UserEntity() {
        System.out.println("对象初始化...");
//        throw new RuntimeException();
    }

    public UserEntity(String name){
        System.out.println("name:"+name);
        this.userName=name;
    }

    /**
     * 思考：在构造函数中，如果发生异常，对象会创建成功吗？
     * 不会。
     * 思考：怎么防止被反射？
     * 设置成私有 单例模式
     *
     * @param args
     */
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        //创建对象
        //1、初始化的操作 无参构造函数 new()
//        UserEntity userEntity=new UserEntity();
//        userEntity.userName="薛白";
//        System.out.println(userEntity.userName);

        //2、使用JAVA的反射机制创建对象
        //路径是 类的完整路径
        Class forName = Class.forName("cn.meredith.day09.UserEntity");
        //使用反射机制创建对象
//        UserEntity userEntity = (UserEntity) forName.newInstance();
//        userEntity.userName = "反射对象";
//        System.out.println(userEntity.userName);

        //反射应用场景
        //1、jdbc连接 springIOC底层使用反射机制+DOM4J
        //2、框架hibernate、mybatis使用到反射机制

        //使用反射机制获取类的方法信息
//        Method[] methods = forName.getMethods();
//        for (Method method : methods) {
//            System.out.println(method.getName());
//        }

        //获取类的属性
//        Field[] fields = forName.getDeclaredFields();
//        for (Field field : fields) {
//            System.out.println(field.getName());
//        }

        //有参的构造函数
        Constructor constructor =forName.getConstructor(String.class);
        //允许访问私有成员
//        constructor.setAccessible(true);
        UserEntity userEntity=(UserEntity) constructor.newInstance("薛白");
        System.out.println(userEntity.userName);
    }
}
