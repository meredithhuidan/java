package cn.meredith.day12.clone;

/**
 * 原型模式
 *
 * 浅拷贝：只会拷贝基本类型，不会拷贝引用类型
 * 而引用类型复制之后，还是被共同引用
 * String不是基本类型 被final修饰了，不能被更改
 *
 * @author Meredith
 * @date
 */
public class ClientTest {

    public static void main(String[] args) throws CloneNotSupportedException {

        //被克隆的对象
        Book book1=new Book();
        book1.setTitle("图书1");
        book1.setPage(10);
        book1.addImg("图片1");
        book1.showBook();

        //开始克隆对象 以原型方式进行复制拷贝
        Book book2= (Book) book1.clone();
        book2.setTitle("图书2");
        book2.setPage(20);
        book2.addImg("图片2");
        book2.showBook();

        //打印原型对象
        book1.showBook();

        //book1与book2的内存地址不一致
        System.out.println(book1);
        System.out.println(book2);
        System.out.println(book1==book2);
    }
}
