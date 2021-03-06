package cn.meredith.day19.spring.ext;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

/**
 * 自定义spring容器框架 xml方式实现
 *
 * @author Meredith
 * @date
 */
public class ExtClassPathXmlApplicationContext {

    //xml读取的路径地址
    private String xmlPath;

    public ExtClassPathXmlApplicationContext(String xmlPath) {
        this.xmlPath = xmlPath;
    }

    //重构+设计模式
    public Object getBean(String beadId) throws Exception {
        if (StringUtils.isEmpty(beadId)) {
            throw new Exception("beanId不能为空!");
        }
        //1、解析xml文件
        List<Element> readerXML = readerXML();
        if (readerXML == null || readerXML.isEmpty()) {
            throw new Exception("配置文件中没有配置bean信息！");
        }
        //2、使用方法参数beanid查找配置文件中bean节点的id信息是否一致
        String className=findByElementClass(readerXML,beadId);
        //3、获取class信息地址,使用反射机制初始化对象
        if (StringUtils.isEmpty(className)){
            throw new Exception("该bean对象没有配置class地址");
        }
        return newInstance(className);
    }

    /**
     * 使用方法参数beanid查找配置文件中bean节点的id信息是否一致
     * @param readerXML
     */
    public String  findByElementClass(List<Element> readerXML, String beadId) throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        for (Element element : readerXML) {
            //获取属性信息
            String xmlBeanId = element.attributeValue("id");
            if (StringUtils.isEmpty(xmlBeanId)) {
                continue;
            }
            if (xmlBeanId.equals(beadId)) {
                String xmlClass = element.attributeValue("class");
               return xmlClass;
            }
        }
        return null;
    }

    /**
     * 解析xml文件
     *
     * @return
     * @throws DocumentException
     */
    public List<Element> readerXML() throws DocumentException {
        //1、解析xml文件信息
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(getResourceAsStream(xmlPath));
        //2、读取根节点
        Element rootElement = document.getRootElement();
        //3、获取根节点下所有的子节点
        List<Element> elements = rootElement.elements();
        return elements;
    }

    /**
     * 初始化对象
     *
     * @param className
     * @return
     */
    public Object newInstance(String className) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Class classInfo = Class.forName(className);
        return classInfo.newInstance();
    }

    /**
     * 获取当前项目路径
     *
     * @param xmlPath
     * @return
     */
    public InputStream getResourceAsStream(String xmlPath) {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(xmlPath);
        return inputStream;
    }

}
