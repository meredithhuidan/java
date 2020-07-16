package cn.meredith.day15.classes;

public class User {

    private String name;
    private Integer age;

    //显式保留无参构造函数
    public User(){

    }

    public User(String name,Integer age){
        this.name=name;
        this.age=age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
