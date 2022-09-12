package cn.org.sqx.mall.product.webapi.mapper;

import static org.junit.jupiter.api.Assertions.*;

import cn.org.sqx.mall.pojo.entity.Category;
import cn.org.sqx.mall.pojo.vo.CategorySimpleVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

/**
 * @auther: sqx
 * @Date: 2022-09-12
 */
@SpringBootTest
public class CategoryMapperTests {

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


    @Test
    @Sql({"classpath:truncate.sql", "classpath:insert_data.sql"})
    public void testGetByNameSuccessfully() {
        // 测试数据
        String name = "类别001";
        // 断言不会抛出异常
        assertDoesNotThrow(() -> {
            // 执行查询
            CategorySimpleVO category = mapper.getByName(name);
            // 断言查询结果不为null
            assertNotNull(category);
        });
    }

    @Test
    @Sql({"classpath:truncate.sql"})
    public void testGetByNameFailBecauseNotFound() {
        // 测试数据
        String name = "类别999";
        // 断言不会抛出异常
        assertDoesNotThrow(() -> {
            // 执行查询
            CategorySimpleVO category = mapper.getByName(name);
            // 断言查询结果为null
            assertNull(category);
        });
    }







}

