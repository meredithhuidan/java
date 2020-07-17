package cn.meredith.day17;

import cn.meredith.day17.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    //spring 事务封装？ aop技术
    public void add() {
//        System.out.println("开始事务");
        System.out.println("往数据库添加数据...");
//        System.out.println("结束事务");

        //只有第一个插入成功
        //应该全部插入 或者全部失败，做回滚 否则会有数据不一致问题
        //用事务来做处理
        userDao.add("test001",20);
//        System.out.println("开始报错啦！");
//        int i=1/0;
        System.out.println("#####");
        userDao.add("test002",30);
    }
}
