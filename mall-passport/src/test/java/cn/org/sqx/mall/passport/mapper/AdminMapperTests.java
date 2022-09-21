package cn.org.sqx.mall.passport.mapper;

import cn.org.sqx.mall.pojo.vo.AdminLoginVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

/**
 * @auther: sqx
 * @Date: 2022-09-21
 */
@SpringBootTest
public class AdminMapperTests {
    @Autowired
    AdminMapper mapper;

    @Sql({"classpath:truncate.sql", "classpath:insert_data.sql"})
    @Test
    void testGetLoginInfoByUsernameSuccessfully() {
        // 测试数据
        String username = "root";
        // 断言不会抛出异常
        Assertions.assertDoesNotThrow(() -> {
            // 执行查询
            AdminLoginVO admin = mapper.getLoginInfoByUsername(username);
            System.out.println("result >>> " + admin);
            // 断言查询结果不为null
            Assertions.assertNotNull(admin);
        });
    }

    @Sql({"classpath:truncate.sql"})
    @Test
    void testGetLoginInfoByUsernameFailBecauseNotFound() {
        // 测试数据
        String username = "root";
        // 断言不会抛出异常
        Assertions.assertDoesNotThrow(() -> {
            // 执行查询
            AdminLoginVO admin = mapper.getLoginInfoByUsername(username);
            System.out.println("result >>> " + admin);
            // 断言查询结果为null
            Assertions.assertNull(admin);
        });
    }
}
