package cn.meredith.day11.builder;

/**
 * Builder
 * （给出一个抽象接口，以规范产品对象的各个组成成分的建造。
 * 这个接口规定要实现复杂对象的哪些部分的创建，并不涉及具体的对象部件的创建）
 *
 * 创建人体buidler
 * 在使用设计模式的时候，一定要学会使用接口和抽象类
 *
 * @author Meredith
 * @date
 */
public interface PersonBuilder {

    //构建头部
    void builderHead();

    //构建体部
    void builderBody();

    //构建尾部
    void builderFoot();

    //组装人物
    Person builderPerson();
}
