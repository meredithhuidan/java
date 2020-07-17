package cn.meredith.day17.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * 操作数据库
 * jdbcTemplate
 *
 */
@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void add(String name,Integer age){
        String sql="INSERT INTO t_user(NAME,AGE) VALUES(?,?);";
        int updateResult=jdbcTemplate.update(sql,name,age);
        System.out.println("updateResult:"+updateResult);
    }
}
