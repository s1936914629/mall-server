package cn.org.sqx.mall.product.webapi.service;

import cn.org.sqx.mall.common.ex.ServiceException;
import cn.org.sqx.mall.pojo.dto.CategoryAddNewDTO;
import cn.org.sqx.mall.pojo.entity.Category;
import cn.org.sqx.mall.pojo.vo.CategorySimpleVO;
import cn.org.sqx.mall.product.service.ICategoryService;
import cn.org.sqx.mall.product.webapi.mapper.CategoryMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @auther: sqx
 * @Date: 2022-09-12
 */
@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public void addNew(CategoryAddNewDTO categoryAddNewDTO) {
        // 从参数中取出尝试添加的类别的名称
        String name = categoryAddNewDTO.getName();
        // 调用categoryMapper.getByName()方法查询
        CategorySimpleVO queryResult = categoryMapper.getByName(name);
        // 判断查询结果是否不为null
        if (queryResult != null) {
            // 是：抛出ServiceException
            throw new ServiceException();
        }

        // 从参数中取出父级类别的id：parentId
        Long parentId = categoryAddNewDTO.getParentId();
        // 判断parentId是否为0，当前尝试新增的类别的depth默认为1
        Integer depth = 1;

        CategorySimpleVO parentCategory = null;
        if (parentId != 0) {
            // 否：此次尝试添加的不是一级类别，则应该存在父级类别，调用categoryMapper.getById()方法查询父级类别的信息
            parentCategory = categoryMapper.getById(parentId);

            // -- 判断查询结果是否为null
            if(parentCategory == null){
                // -- 是：抛出ServiceException
                throw new ServiceException();
            }
            // -- 否：当前depth >>> 父级depth + 1
            depth = parentCategory.getDepth() + 1;

        }

        // 创建Category对象
        Category category = new Category();
        // 调用BeanUtils.copyProperties()将参数对象中的属性值复制到Category对象中
        BeanUtils.copyProperties(categoryAddNewDTO,category);
        // 补全Category对象中的属性值：depth >>> 前序运算结果
        category.setDepth(depth);
        // 补全Category对象中的属性值：enable >>> 1（默认即启用）
        category.setEnable(1);
        // 补全Category对象中的属性值：isParent >>> 0
        category.setIsParent(0);
        // 补全Category对象中的属性值：gmtCreate, gmtModified >>> LocalDateTime.now()
        LocalDateTime time = LocalDateTime.now();
        category.setGmtCreate(time);
        category.setGmtModified(time);
        // 调用categoryMapper.insert(Category)插入类别数据，获取返回的受影响的行数
        int insert = categoryMapper.insert(category);
        // 判断返回的受影响的行数是否不为1
        if(insert != 1){
            // 是：抛出ServiceException
            throw new ServiceException();
        }

        // 判断父级类别的isParent是否为0
        // 以下判断条件有部分多余，但不会报错
        if(parentId != 0 && parentCategory != null && parentCategory.getIsParent()!=0){
            // 是：调用categoryMapper.updateIsParentById()方法，将父级类别的isParent修改为1，获取返回的受影响的行数
            insert = categoryMapper.updateIsParentById(parentId, 1);
            // 判断返回的受影响的行数是否不为1
            if(insert != 1){
                // 是：抛出ServiceException
                throw new ServiceException();
            }
        }

    }

}


