package top.zhangxiaofeng.jdbcTemplate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author zhangxiaofeng
 * @Date 2021/5/15
 */
@SpringBootTest
public class JdbcTemplateTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void count() {
        Long count = jdbcTemplate.queryForObject("select count(*) from dept", Long.class);
        System.out.println("count=" + count);
    }
}
