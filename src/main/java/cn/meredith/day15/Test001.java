package cn.meredith.day15;

/**
 * jdk可视化工具jconsole、visualVm的熟悉了解
 *
 * @author Meredith
 * @date
 */
public class Test001 {

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            Thread.sleep(1000);
        }
    }
}
