package cn.org.sqx.mall.product.webapi.repository;

import cn.org.sqx.mall.pojo.vo.CategoryDetailsVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @auther: sqx
 * @Date: 2022-09-20
 */

@SpringBootTest
public class CategoryRedisRepositoryTests {

    @Autowired
    ICategoryRedisRepository repository;

    @Test
    void testGetDetailsByIdSuccessfully() {
        testSave();

        Long id = 10L;
        CategoryDetailsVO category = repository.getDetailsById(id);
        Assertions.assertNotNull(category);
    }

    @Test
    void testGetDetailsByIdReturnNull() {
        Long id = -1L;
        CategoryDetailsVO category = repository.getDetailsById(id);
        Assertions.assertNull(category);
    }

    private void testSave() {
        CategoryDetailsVO category = new CategoryDetailsVO();
        category.setId(10L);
        category.setName("家用电器");

        Assertions.assertDoesNotThrow(() -> {
            repository.save(category);
        });
    }
}
