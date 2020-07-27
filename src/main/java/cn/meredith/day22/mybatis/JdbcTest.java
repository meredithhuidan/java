package cn.meredith.day22.mybatis;

import cn.meredith.day22.mybatis.util.JDBCUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * JDBC基础知识
 *
 * @author Meredith
 * @date
 */
public class JdbcTest {

    public static void main(String[] args) throws SQLException {

        // #{userName} mybatis替换成？
        String insertSql = "insert into t_user(Name,Age) value(?,?);";
        //线程安全问题，最好不要用ArrayList，用数组或其他安全性方式  可变数组
        ArrayList arrayList = new ArrayList<>();
        arrayList.add("薛白");
        arrayList.add(20);

        //插入
        int insert = JDBCUtils.insert(insertSql, false, arrayList);
        System.out.println("insert:" + insert);

        //查询
        String querySql = "select * from t_user where Name=? and Age=?";
        ResultSet resultSet = JDBCUtils.query(querySql, arrayList);
        while (resultSet.next()) {
            Integer id = resultSet.getInt("Age");
            String userName = resultSet.getString("Name");
            System.out.println("id:" + id + ",userName:" + userName);
        }
    }
}
