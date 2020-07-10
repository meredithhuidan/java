package cn.meredith.day06;

/**
 * 合理配置线程池
 *
 * @author Meredith
 * @date
 */
public class Test001 extends Thread{

    @Override
    public void run() {

        //特别多代码......没有阻塞情况

        //阻塞产生的原因：请求、数据库、循环 IO操作可能会发生阻塞
        //http.post()---阻塞2秒

        //IO密集:该任务需要大量的IO操作，产生阻塞，如果在单线程中执行阻塞，可以使用多线程技术
        //多线程下，cpu运算能力，不会浪费等待资源
        //IO密集时，多配置线程数，一般配置（2*cpu核数）-----最大线程数

        //cpu密集：该任务不会产生大量IO阻塞，cpu运行的时候特别快
        //cpu密集时，线程没有等待，配置线程数=cpu核数
    }
}
