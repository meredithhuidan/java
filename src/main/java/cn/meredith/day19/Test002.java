package cn.meredith.day19;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

/**
 * 解析xml
 *
 * @author Meredith
 * @date
 */
public class Test002 {

    public static void main(String[] args) throws DocumentException {
        new Test002().Test001();
    }

    public void Test001() throws DocumentException {
        SAXReader saxReader=new SAXReader();
        //读取xml文件
        Document document =saxReader.read(getResourceAsStream("student.xml"));
        //读取根节点
        Element rootElement =document.getRootElement();
        getNodes(rootElement);
    }

    public void getNodes(Element rootElement){
        System.out.println("获取节点名称："+rootElement.getName());
        //获取属性方法
        List<Attribute> attributes =rootElement.attributes();
        for (Attribute attribute:attributes){
            System.out.println(attribute.getName()+"---"+attribute.getText());
        }
        //获取属性value
        String textTrim=rootElement.getTextTrim();
        if (StringUtils.isEmpty(textTrim)){
            System.out.println("textTrim:"+textTrim);
        }
        //使用迭代器，获取子节点信息
        Iterator<Element>  elementIterator=rootElement.elementIterator();
        while (elementIterator.hasNext()){
            Element next=elementIterator.next();
            getNodes(next);
        }
    }

    /**
     * 获取当前项目路径
     * @param xmlPath
     * @return
     */
    public InputStream getResourceAsStream(String xmlPath){
        InputStream inputStream =this.getClass().getClassLoader().getResourceAsStream(xmlPath);
        return inputStream;
    }
}
