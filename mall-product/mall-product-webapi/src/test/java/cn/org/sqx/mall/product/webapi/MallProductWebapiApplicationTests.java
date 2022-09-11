package cn.org.sqx.mall.product.webapi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

/**
 * @auther: sqx
 * @Date: 2022-09-11
 */
@SpringBootTest
public class MallProductWebapiApplicationTests {
    @Autowired
    DataSource dataSource;

    @Test
    void contextLoads(){

    }

    @Test
    void testConnection(){
        Assertions.assertDoesNotThrow(()->{
            dataSource.getConnection();
        });
    }
}
