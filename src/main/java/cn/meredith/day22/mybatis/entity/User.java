package cn.meredith.day22.mybatis.entity;

/**
 * 实体User
 * 跟数据库中字段命名一样
 * 数据库中字段名默认小写
 *
 * @author Meredith
 * @date
 */
public class User {

//    private Integer id;

    private String name;

    private Integer age;

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

    //    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
}
