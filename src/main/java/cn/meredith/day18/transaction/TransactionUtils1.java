package cn.meredith.day18.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

/**
 * 编程事务
 * 需要手动beigin ，手动回滚，手动提交
 * 不要用单例，用原型，因为这个类要共享，很容易发生线程安全问题
 *
 * @author Meredith
 * @date
 */
@Component //注入spring容器里
@Scope("prototype")     //设置为原型,每个事务都是新的实例，目的是解决线程安全问题，多例的
public class TransactionUtils1 {

    //spring默认是单例的，设置为原型
    //全局接收事务状态
    private TransactionStatus transactionStatus;

    //获取事务源
    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    //开启事务
    public TransactionStatus begin() {
        System.out.println("开启事务");
        TransactionStatus transaction = dataSourceTransactionManager.getTransaction(new DefaultTransactionAttribute());
        return transaction;
    }

    //提交事务
    public void commit(TransactionStatus transaction) {
        System.out.println("提交事务");
        dataSourceTransactionManager.commit(transaction);
    }

    //回滚事务
    public void rollback() {
        System.out.println("回滚事务");
        dataSourceTransactionManager.rollback(transactionStatus);
    }

}
