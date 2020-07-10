package cn.meredith.day04;

/**
 * 多线程环境下，重排序可能会产生的问题
 *
 * @author Meredith
 * @date
 */
public class ReorderExample {

    //为解决重排序导致的结果出现错误问题，在变量之前加volatile
    volatile int a=0;
    volatile boolean flag=false;

    //两个线程并行执行
    //写入的线程
    public void writer(){
        a=1;    //1
        flag=true;  //2
    }
    //1，2行代码没有任何依赖关系
    //可能重排序，2在1之前

    //读取的线程
    public void reader(){
        if (flag){  //3
            int i=a*a;  //4     //重排序之后可能会为0
        }
    }

}
