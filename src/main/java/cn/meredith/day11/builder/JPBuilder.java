package cn.meredith.day11.builder;

/**
 * 日本人
 */
public class JPBuilder implements PersonBuilder {

    private Person person;

    public JPBuilder(){
        //创建一个Person实例,用于调用set方法
        person=new Person();
    }

    public void builderHead() {
        person.setHead("日本人 圆脸");
    }

    public void builderBody() {
        person.setBody("日本人 矮");
    }

    public void builderFoot() {
        person.setFoot("日本人 腿短");
    }

    public Person builderPerson() {
        return person;
    }
}
