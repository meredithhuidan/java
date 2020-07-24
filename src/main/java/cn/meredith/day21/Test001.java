package cn.meredith.day21;

import cn.meredith.day21.connection.ConnectionPoolManager;

import java.sql.Connection;
import java.util.List;
import java.util.Vector;

/**
 * 白话文描述 数据库连接池实现原理
 * 核心参数
 * 1、空闲连接  容器 （没有被使用的连接存放）
 * 2、活动连接  容器 （正在使用的连接）
 * 核心步骤
 * 1、初始化线程池 （初始化空闲线程）
 * 2、调用getConnection方法 ————获取连接
 * 2.1、首先去freeConnection获取当前连接，存放在activeConnection中
 * 3、调用releaseConnection方法 ————释放连接 __资源的回收
 * 3.1、获取activeConnection集合连接，转移到freeConnection中
 *
 * @author Meredith
 * @date
 */
public class Test001 {

    //使用线程安全的集合 空闲线程
    private List<Connection> freeConnection = new Vector<Connection>();
    //使用线程安全的集合 活动线程
    private List<Connection> activeConnection = new Vector<Connection>();

    public static void main(String[] args) {
        ThreadConnection threadConnection=new ThreadConnection();
        for (int i = 0; i < 3; i++) {
            Thread thread=new Thread(threadConnection,"线程i:"+i);
            thread.start();
        }
    }
}

class ThreadConnection implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            Connection connection = ConnectionPoolManager.getConnection();
            System.out.println(Thread.currentThread().getName() + ",connection:" + connection);
            ConnectionPoolManager.releaseConnection(connection);
        }
    }
}
