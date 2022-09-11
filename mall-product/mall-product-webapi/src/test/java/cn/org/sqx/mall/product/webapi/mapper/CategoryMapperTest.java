package cn.org.sqx.mall.product.webapi.mapper;

import cn.org.sqx.mall.pojo.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @auther: sqx
 * @Date: 2022-09-11
 */
@SpringBootTest
public class CategoryMapperTest {
    @Autowired
    CategoryMapper mapper;

    @Test
    @Sql("classpath:truncate.sql")
    public void testInsert() {
        // 测试数据
        Category category = new Category();
        category.setName("手机");
        // 断言不会抛出异常
        assertDoesNotThrow(() -> {
            int rows = mapper.insert(category);
            assertEquals(1, rows);
            assertEquals(1, category.getId());
        });
    }
}
