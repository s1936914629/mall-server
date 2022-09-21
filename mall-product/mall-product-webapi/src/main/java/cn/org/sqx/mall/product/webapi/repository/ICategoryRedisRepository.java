package cn.org.sqx.mall.product.webapi.repository;

import cn.org.sqx.mall.pojo.vo.CategoryDetailsVO;

/**
 * @auther: sqx
 * @Date: 2022-09-20
 */
public interface ICategoryRedisRepository {

    /**
     * 类别数据的KEY的前缀
     */
    String KEY_CATEGORY_ITEM_PREFIX = "categories:item:";

    /**
     * 判断是否存在id对应的缓存数据
     *
     * @param id 类别id
     * @return 存在则返回true，否则返回false
     */
    Boolean exists(Long id);

    /**
     * 向缓存中写入某id对应的空数据（null），此方法主要用于解决缓存穿透问题
     *
     * @param id 类别id
     */
    void saveEmptyValue(Long id);

    /**
     * 将类别详情存入到Redis中
     *
     * @param category 类别列表
     */
    void save(CategoryDetailsVO category);

    /**
     * 根据类别id获取类别详情
     *
     * @param id 类别id
     * @return 匹配的类别详情，如果没有匹配的数据，则返回null
     */
    CategoryDetailsVO getDetailsById(Long id);

}
