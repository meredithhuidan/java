package cn.meredith.day10.proxy;

/**
 * 静态代理
 * 静态需要生成代理对象
 */
public class UserDaoProxy implements IUserDao {

    private IUserDao iUserDao;

    public UserDaoProxy(IUserDao iUserDao){
        this.iUserDao=iUserDao;
    }

    //在方法之前之后做一个拦截处理
    public void add() {
        System.out.println("开启事务");
        iUserDao.add();
        System.out.println("提交事务");
    }

    public void save() {
        System.out.println("开启事务");
        iUserDao.save();
        System.out.println("提交事务");
    }
}
