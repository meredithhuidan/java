package cn.meredith.day18.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class LogDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void add(String name) {
        String sql = "INSERT INTO t_log(log_name) values(?)";
        int updateResult = jdbcTemplate.update(sql, name);
        System.out.println("##logDao###updateResult" + updateResult);
    }
}
