package cn.meredith.day20.springmvc;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 手写springmvc 原理分析
 * 1、创建一个前端控制器 ExtDispatcherServlet,拦截所有请求（springmvc 基于servlet实现）
 * 2、初始化操作：重写servlet init()方法
 *      2.1、将扫包范围所有的类，注入到springmvc容器里面，存放在Map集合中，key默认类名小写，value为创建好的对象
 *      2.2、将URL映射和方法进行关联。
 *              2.2.1、解析类上是否有注解，使用java反射机制循环遍历方法，判断方法上是否存在注解，进行封装url和方法对应存入集合中
 * 3、处理请求 重写Get或者Post方法
 *      3.1、获取请求URL,去urlBeans集合中获取实例对象，获取成功实例对象后，调用urlMethods集合获取方法名称，使用反射机制执行
 *
 * @author Meredith
 * @date
 */
public class Test001 {
    //springmvc容器对象 key:类名id value:对象
    private ConcurrentHashMap<Object ,String > springmvcBeans=new ConcurrentHashMap();
    //key:请求地址 value:value类
    private ConcurrentHashMap<Object ,String > urlBeans=new ConcurrentHashMap();
    //key:请求地址 value:value方法名称
    private ConcurrentHashMap<Object ,String > urlMethods=new ConcurrentHashMap();



}
