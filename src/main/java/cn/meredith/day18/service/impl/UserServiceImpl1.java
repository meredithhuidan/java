package cn.meredith.day18.service.impl;

import cn.meredith.day17.dao.UserDao;
import cn.meredith.day18.annotation.ExtTransactional;
import cn.meredith.day18.service.LogService;
import cn.meredith.day18.service.UserService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
public class UserServiceImpl1 implements UserService1 {

    @Autowired
    private UserDao userDao;

    @Autowired
    private LogService logService;

    //声明式事务 @Transactional 或者XML方式
    //方法开始执行前，开启提交事务
    @Transactional
//    @ExtTransactional //自定义事务
    public void add() {
        //不能抛异常 否则会没法抛出 导致事务失效之谜
//        try{
        //日志不可能回滚 后面程序发生错误，不能影响到我的回滚
        //正常当addLog()方法执行完毕，就应该提交事务
        //调用接口的时候，调用接口失败，需要回滚 但日志记录不需要回滚
        logService.addLog();
        userDao.add("test001", 20);
        int i = 1 / 0;
        System.out.println("############");
        userDao.add("test002", 30);
//        }catch (Exception e){
//        //获取当前事务，手动设置回滚
//        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//        }

        //注意：begin之后，一定commit或rollback
        //以为事务一直存在，占内存，可能会产生死锁现象

    }
    //方法执行完毕之后，才会提交事务


}
