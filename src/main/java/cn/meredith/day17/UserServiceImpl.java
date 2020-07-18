package cn.meredith.day17;

import cn.meredith.day17.dao.UserDao;
import cn.meredith.day17.transaction.TransactionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private TransactionUtils transactionUtils;

    //spring 事务封装？ aop技术
    public void add() {
        TransactionStatus transactionStatus=null;

        //注意事项： 在使用spring事务时，service层不要try,将异常抛出给外层aop 异常通知接受回滚
        try {
//            System.out.println("开始事务");
            System.out.println("往数据库添加数据...");
//            System.out.println("结束事务");

            //只有第一个插入成功
            //应该全部插入 或者全部失败，做回滚 否则会有数据不一致问题
            //用事务来做处理
            //开启事务
            transactionStatus=transactionUtils.begin();
            userDao.add("test001", 20);
            System.out.println("开始报错啦！");
//            int i = 1 / 0;
//            System.out.println("#####");
            userDao.add("test002", 30);
            //提交事务
            if (transactionStatus!=null)
                transactionUtils.commit(transactionStatus);

        } catch (Exception e) {
            e.getMessage();
            //回滚事务
            if (transactionStatus!=null)
                transactionUtils.rollback(transactionStatus);
        }finally {

        }
    }
}
