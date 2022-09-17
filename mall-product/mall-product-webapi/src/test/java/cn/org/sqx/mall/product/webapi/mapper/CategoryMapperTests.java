package cn.org.sqx.mall.product.webapi.mapper;

import static org.junit.jupiter.api.Assertions.*;

import cn.org.sqx.mall.pojo.entity.Category;
import cn.org.sqx.mall.pojo.vo.CategorySimpleListItemVO;
import cn.org.sqx.mall.pojo.vo.CategorySimpleVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

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
    @Sql({"classpath:truncate.sql", "classpath:insert_data.sql"})
    public void testUpdateIsParentByIdSuccessfully() {
        // 测试数据
        Long id = 1L;
        Integer isParent = 1;
        // 断言不会抛出异常
        assertDoesNotThrow(() -> {
            // 执行测试
            int rows = mapper.updateIsParentById(id, isParent);
            // 断言受影响的行数为1
            assertEquals(1, rows);
        });
    }

    @Test
    @Sql({"classpath:truncate.sql"})
    public void testUpdateIsParentByIdFailBecauseNotFound() {
        // 测试数据
        Long id = -1L;
        Integer isParent = 1;
        // 断言不会抛出异常
        assertDoesNotThrow(() -> {
            // 执行测试
            int rows = mapper.updateIsParentById(id, isParent);
            // 断言受影响的行数为0
            assertEquals(0, rows);
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

    @Test
    @Sql({"classpath:truncate.sql", "classpath:insert_data.sql"})
    public void testGetByIdSuccessfully() {
        // 测试数据
        Long id = 1L;
        // 断言不会抛出异常
        assertDoesNotThrow(() -> {
            // 执行查询
            CategorySimpleVO category = mapper.getById(id);
            // 断言查询结果不为null
            assertNotNull(category);
        });
    }

    @Test
    @Sql({"classpath:truncate.sql"})
    public void testGetByIdFailBecauseNotFound() {
        // 测试数据
        Long id = -1L;
        // 断言不会抛出异常
        assertDoesNotThrow(() -> {
            // 执行查询
            CategorySimpleVO category = mapper.getById(id);
            // 断言查询结果为null
            assertNull(category);
        });
    }

    @Test
    @Sql({"classpath:truncate.sql", "classpath:insert_data.sql"})
    public void testListByParentId() {
        // 测试数据
        Long parentId = 1L;
        // 执行测试，获取查询结果
        List<CategorySimpleListItemVO> list = mapper.listByParentId(parentId);
        // 查看结果
        System.out.println("查询结果数量：" + list.size());
        for (CategorySimpleListItemVO item : list) {
            System.out.println(item);
        }
    }

    @Test
    @Sql({"classpath:truncate.sql", "classpath:insert_data.sql"})
    public void testGetDetailsByIdSuccessfully() {
        // 测试数据
        Long id = 1L;
        // 断言不会抛出异常
        assertDoesNotThrow(() -> {
            // 执行查询
            Object category = mapper.getDetailsById(id);
            // 断言查询结果不为null
            assertNotNull(category);
        });
    }

    @Test
    @Sql({"classpath:truncate.sql"})
    public void testGetDetailsByIdFailBecauseNotFound() {
        // 测试数据
        Long id = -1L;
        // 断言不会抛出异常
        assertDoesNotThrow(() -> {
            // 执行查询
            Object category = mapper.getDetailsById(id);
            // 断言查询结果为null
            assertNull(category);
        });
    }




}

