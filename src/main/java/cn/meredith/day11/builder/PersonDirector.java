package cn.meredith.day11.builder;

/**
 * Director
 * （调用具体建造者来创建复杂对象的各个部分，在指导者中不涉及具体产品的信息，只负责保证对象各部分完整创建或按某种顺序创建）
 *
 * 构建任务，整合所有部件
 *
 * @author Meredith
 * @date
 */
public class PersonDirector {

    public Person createPerson(PersonBuilder personBuilder){
        personBuilder.builderHead();
        personBuilder.builderBody();
        personBuilder.builderFoot();
        return personBuilder.builderPerson();
    }

    public static void main(String[] args) {
        PersonDirector personDirector=new PersonDirector();
        Person person=personDirector.createPerson(new JPBuilder());
        System.out.println(person.getHead());
        System.out.println(person.getBody());
        System.out.println(person.getFoot());
    }
}
