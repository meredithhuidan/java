package cn.meredith.day16;

/**
 * 1、静态代码块优先于静态变量
 * 2、类构造器<clinit>（）方法是由编译器自动收藏类中的所有类变量的赋值动作和静态语句块(static块)中的语句合并产生，代码从上往下执行
 * 3、静态只初始化一次
 */
public class Test001 {

    //静态代码块
    static {
        age = 700;
        System.out.println("2静态代码块初始化");
    }

    //最开始 int age =0;
    private static int age = 500;

    public Test001() {
        System.out.println("1构造函数");
    }

    public static void main(String[] args) {
        Test001 test001 = new Test001();
        Test001 test002 = new Test001();
        System.out.println(test001.age);
    }
}
