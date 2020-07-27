package cn.meredith.day22.mybatis;

import cn.meredith.day22.mybatis.entity.User;
import cn.meredith.day22.mybatis.mapper.UserMapper;
import cn.meredith.day22.mybatis.sql.SqlSession;

/**
 * 使用动态代理技术虚拟调用方法
 *
 * @author Meredith
 * @date
 */
public class Test002 {

    public static void main(String[] args) {

        //使用动态代理技术虚拟调用方法
        UserMapper userMapper = SqlSession.getMapper(UserMapper.class);
        //先拦截invoke
//        int insertUserResult = userMapper.insertUser("文征明", 23);
//        System.out.println("insertUserResult:" + insertUserResult);

        User user=userMapper.selectUser("文征明", 23);
//        System.out.println("结果："+user.getUserName()+","+user.getUserAge()+","+user.getId());
        System.out.println("结果："+user.getName()+","+user.getAge());
    }
}
