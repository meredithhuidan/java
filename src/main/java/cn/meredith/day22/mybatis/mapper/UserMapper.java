package cn.meredith.day22.mybatis.mapper;

import cn.meredith.day22.mybatis.annotation.ExtInsert;
import cn.meredith.day22.mybatis.annotation.ExtParam;
import cn.meredith.day22.mybatis.annotation.ExtSelect;
import cn.meredith.day22.mybatis.entity.User;

import java.util.List;

public interface UserMapper {

    @ExtInsert("insert into t_user(Name,Age) values(#{userName},#{userAge})")
    public int insertUser(@ExtParam("userName") String userName, @ExtParam("userAge") Integer userAge);

    @ExtSelect("select * from t_user where Name=#{userName} and Age=#{userAge}")
    User selectUser(@ExtParam("userName") String userName, @ExtParam("userAge") Integer userAge);

    //如果返回是list<>集合，returnType判断是List，定义一个ArrayList
    //List<User> selectUser(@ExtParam("userName") String userName, @ExtParam("userAge") Integer userAge);

}
