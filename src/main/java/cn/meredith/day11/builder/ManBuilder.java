package cn.meredith.day11.builder;

/**
 * 构造人物 外国人
 */
public class ManBuilder implements PersonBuilder {

    public void builderHead() {
        System.out.println("美国人 头部 鼻子尖、长脸、蓝眼睛");
    }

    public void builderBody() {
        System.out.println("美国人 长得高，块头大");
    }

    public void builderFoot() {
        System.out.println("美国人 腿长...");
    }

    public Person builderPerson() {
        return null;
    }
}
