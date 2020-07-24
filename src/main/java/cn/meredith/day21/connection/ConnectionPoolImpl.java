package cn.meredith.day21.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Vector;

/**
 * 白话文描述 数据库连接池实现原理
 * * 核心参数
 * * 1、空闲连接  容器 （没有被使用的连接存放）
 * * 2、活动连接  容器 （正在使用的连接）
 * * 核心步骤
 * * 1、初始化线程池 （初始化空闲线程）
 * * 2、调用getConnection方法 ————获取连接
 * *      2.1、首先去freeConnection获取当前连接，存放在activeConnection中
 * * 3、调用releaseConnection方法 ————释放连接 __资源的回收
 * *      3.1、获取activeConnection集合连接，转移到freeConnection中
 *
 * @author Meredith
 * @date
 */
public class ConnectionPoolImpl implements ConnectionPool {

    //使用线程安全的集合 空闲线程
    private List<Connection> freeConnection = new Vector<Connection>();
    //使用线程安全的集合 活动线程
    private List<Connection> activeConnection = new Vector<Connection>();

    private DbBean dbBean;

    //计算连接的线程数
    private int countConnect = 0;

    public ConnectionPoolImpl(DbBean dbBean) {
        //获取配置文件信息
        this.dbBean = dbBean;
    }

    /**
     * 初始化数据库连接池（初始化空闲线程）
     */
    private void init() {
        if (dbBean == null) {
            return; //最好抛出异常
        }
        //1、获取初始化连接数
        for (int i = 0; i < dbBean.getInitConnections(); i++) {
            //2、创建Connection连接
            Connection newConnection = newConnection();
            if (newConnection != null) {
                //3、存放在freeConnection集合
                freeConnection.add(newConnection);
            }
        }
    }

    /**
     * 创建Connection连接
     *
     * @return
     */
    private synchronized Connection newConnection() {
        try {
            Class.forName(dbBean.getDriverName());
            Connection connection = DriverManager.getConnection(dbBean.getUrl(), dbBean.getUserName(), dbBean.getPassword());
            //必须加synchronized
            countConnect++;
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 调用getConnection方法 ————获取连接
     *
     * @return
     */
    @Override
    public synchronized Connection getConnection() {
        try {
            Connection connection=null;
            //怎么知道当前创建的连接>最大连接数 原子类计数
            if (countConnect<dbBean.getMaxActiveConnections()){
                //小于最大活动连接数
                //1、判断空闲线程集合是否有数据
                if (freeConnection.size()>0){
                    //空闲线程有存在连接
                    connection=freeConnection.remove(0);     //拿到再删除

                }else {
                    //创建新的连接
                    connection=newConnection();
                }
                //判断连接是否可用
                boolean available=isAvailable(connection);
                //往活动线程集合存
                if (available){
                    //活动线程+1
                    activeConnection.add(connection);
                }else {
                    countConnect--;
                    //怎么重试？ 递归算法
                    getConnection();
                }

            }else {
                //如果大于最大活动连接数，进行等待
                wait(dbBean.getConnTimeOut());
                //重试
                connection=getConnection();
            }
            return connection;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 判断连接是否可用
     * @param connection
     * @return
     */
    public boolean isAvailable(Connection connection){
        try {
            if (connection==null||connection.isClosed()){
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 释放资源
     * @param connection
     * @return
     */
    @Override
    public synchronized void releaseConnection(Connection connection) {

        try {
            //1、判断连接是否可用
            if (connection==null){
                return;
            }
            if (isAvailable(connection)) {
                //2、判断空闲线程是否已满
                if (freeConnection.size()<dbBean.getMaxConnections()){
                    //空闲线程池没有满
                    freeConnection.add(connection);
                }else{
                    //空闲线程已满
                    connection.close();
                }
                activeConnection.remove(connection);
                countConnect--;
                notifyAll();
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
