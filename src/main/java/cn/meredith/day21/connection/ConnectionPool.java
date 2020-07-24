package cn.meredith.day21.connection;

import java.sql.Connection;

/**
 * 数据库连接池
 *
 * @author Meredith
 * @date
 */
public interface ConnectionPool {

    //获取连接（重复利用机制）
    public Connection getConnection();

    //释放连接（可回收机制）
    public void releaseConnection(Connection connection);

}
