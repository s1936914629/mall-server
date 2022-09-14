package cn.org.sqx.mall.product.service;

import cn.org.sqx.mall.pojo.dto.CategoryAddNewDTO;
import org.springframework.transaction.annotation.Transactional;

/**
 * @auther: sqx
 * @Date: 2022-09-13
 */
public interface ICategoryService {

    /**
     * 添加“类别”
     *
     * @param categoryAddNewDTO 类别
     */
    @Transactional
    void addNew(CategoryAddNewDTO categoryAddNewDTO);


}
