package cn.org.sqx.mall.product.webapi.mapper;

import cn.org.sqx.mall.pojo.entity.Category;
import org.springframework.stereotype.Repository;

/**
 * 处理“类别”数据的持久层接口
 *
 * @auther: sqx
 * @Date: 2022-09-11
 */
@Repository
public interface CategoryMapper {
    /**
     * 插入“类别”数据
     *
     * @param category 类别
     * @return 受影响的行数
     */
    int insert(Category category);

    // 查询--待定
}
