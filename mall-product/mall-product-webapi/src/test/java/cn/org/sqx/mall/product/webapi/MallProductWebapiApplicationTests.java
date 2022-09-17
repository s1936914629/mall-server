package cn.org.sqx.mall.product.webapi;

import cn.org.sqx.mall.common.ex.ServiceException;
import cn.org.sqx.mall.product.webapi.service.CategoryServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @auther: sqx
 * @Date: 2022-09-11
 */
@SpringBootTest
public class MallProductWebapiApplicationTests {
    @Autowired
    DataSource dataSource;

    @Autowired
    CategoryServiceImpl service;

    @Test
    void contextLoads(){

    }

    @Test
    void testConnection(){
        assertDoesNotThrow(()->{
            dataSource.getConnection();
        });
    }

    @Test
    @Sql({"classpath:truncate.sql", "classpath:insert_data.sql"})
    public void testGetDetailsByIdSuccessfully() {
        // 测试数据
        Long id = 1L;
        // 断言不抛出异常
        assertDoesNotThrow(() -> {
            service.getDetailsById(id);
        });
    }

    @Test
    @Sql({"classpath:truncate.sql"})
    public void testGetDetailsByIdFailBecauseNotFound() {
        // 测试数据
        Long id = -1L;
        // 断言抛出异常
        assertThrows(ServiceException.class, () -> {
            service.getDetailsById(id);
        });
    }
}
