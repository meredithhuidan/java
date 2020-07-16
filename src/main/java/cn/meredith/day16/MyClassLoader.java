package cn.meredith.day16;

import java.io.IOException;
import java.io.InputStream;

/**
 * 传递一个class路径地址，读取class文件，将文件放到jvm
 */
public class MyClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        try {
            //1、获取文件名称
            String filename=name.substring(name.lastIndexOf(".")+1)+".class";
            //2、读取文件名称
            InputStream is =this.getClass().getResourceAsStream(filename);
            //3、读取字节
            byte[] bytes= new byte[0];
            bytes = new byte[is.available()];
            is.read(bytes);
            //4、将读取byte数组给jvm识别class对象
            return defineClass(name,bytes,0,bytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException();
        }

    }
}
