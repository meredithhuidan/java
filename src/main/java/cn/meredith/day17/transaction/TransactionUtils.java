package cn.meredith.day17.transaction;

import org.springframework.beans.factory.annotation.Autowired;
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
public class TransactionUtils {

    //获取事务源
    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    //开启事务
    public TransactionStatus begin() {
        TransactionStatus transaction = dataSourceTransactionManager.getTransaction(new DefaultTransactionAttribute());
        return transaction;
    }

    //提交事务
    public void commit(TransactionStatus transaction) {
        dataSourceTransactionManager.commit(transaction);
    }

    //回滚事务
    public void rollback(TransactionStatus transaction) {
        dataSourceTransactionManager.rollback(transaction);
    }

}
