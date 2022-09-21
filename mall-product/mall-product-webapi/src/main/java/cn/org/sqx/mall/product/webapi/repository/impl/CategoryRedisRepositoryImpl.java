package cn.org.sqx.mall.product.webapi.repository.impl;

import cn.org.sqx.mall.pojo.vo.CategoryDetailsVO;
import cn.org.sqx.mall.product.webapi.repository.ICategoryRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * @auther: sqx
 * @Date: 2022-09-20
 */
@Repository
public class CategoryRedisRepositoryImpl implements ICategoryRedisRepository {
    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @Override
    public Boolean exists(Long id) {
        String key = KEY_CATEGORY_ITEM_PREFIX + id;
        return redisTemplate.hasKey(key);
    }

    @Override
    public void saveEmptyValue(Long id) {
        String key = KEY_CATEGORY_ITEM_PREFIX + id;
        redisTemplate.opsForValue().set(key, null);
    }

    @Override
    public void save(CategoryDetailsVO category) {
        String key = KEY_CATEGORY_ITEM_PREFIX + category.getId();
        redisTemplate.opsForValue().set(key, category);
    }

    @Override
    public CategoryDetailsVO getDetailsById(Long id) {
        String key = KEY_CATEGORY_ITEM_PREFIX + id;
        Serializable result = redisTemplate.opsForValue().get(key);
        if (result == null) {
            return null;
        } else {
            CategoryDetailsVO category = (CategoryDetailsVO) result;
            return category;
        }
    }
}
