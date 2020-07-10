package cn.meredith.day10.proxy;

public class Client {

    public static void main(String[] args) {
        IUserDao userDao=new UserDaoImp();
        UserDaoProxy userDaoProxy=new UserDaoProxy(userDao);
        userDaoProxy.add();
    }
}
