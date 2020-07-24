package cn.meredith.day21.connection;

import java.sql.Connection;

/**
 * 管理线程池
 *
 * @author Meredith
 * @date
 */
public class ConnectionPoolManager {

    private static DbBean dbBean=new DbBean();
    private static ConnectionPool connectionPool=new ConnectionPoolImpl(dbBean);

    /**
     * 获取连接（重复利用机制）
     * @return
     */
    public static Connection getConnection(){
        return connectionPool.getConnection();
    }

    /**
     * 释放连接（可回收机制）
     * @param connection
     */
    public static void releaseConnection(Connection connection){
        connectionPool.releaseConnection(connection);
    }
}
