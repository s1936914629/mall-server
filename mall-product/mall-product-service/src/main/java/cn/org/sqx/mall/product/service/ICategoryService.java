package cn.org.sqx.mall.product.service;

import cn.org.sqx.mall.pojo.dto.CategoryAddNewDTO;
import cn.org.sqx.mall.pojo.vo.CategorySimpleListItemVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    /**
     * 根据父级"类别"查询其所有子级"类别"
     *
     * @param parentId 父级"类别"的id
     * @return 此父级"类别"下的所有子级"类别"的列表
     */
    List<CategorySimpleListItemVO> listByParentId(Long parentId);


}
