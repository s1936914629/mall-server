package cn.org.sqx.mall.product.service;

import cn.org.sqx.mall.pojo.dto.CategoryAddNewDTO;
import cn.org.sqx.mall.pojo.vo.CategoryDetailsVO;
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
     * 根据“类别”id查询“类别”的详细信息
     *
     * @param id “类别”id
     * @return “类别”的详细信息，如果没有匹配的数据，将抛出异常
     */
    CategoryDetailsVO getDetailsById(Long id);

    /**
     * 根据父级"类别"查询其所有子级"类别"
     *
     * @param parentId 父级"类别"的id
     * @return 此父级"类别"下的所有子级"类别"的列表
     */
    List<CategorySimpleListItemVO> listByParentId(Long parentId);

    /**
     * 预热类别数据的缓存
     */
    void preloadCache();



}
