package cn.org.sqx.mall.product.webapi.service;

import cn.org.sqx.mall.common.ex.ServiceException;
import cn.org.sqx.mall.common.web.State;
import cn.org.sqx.mall.pojo.dto.CategoryAddNewDTO;
import cn.org.sqx.mall.pojo.entity.Category;
import cn.org.sqx.mall.pojo.vo.CategoryDetailsVO;
import cn.org.sqx.mall.pojo.vo.CategorySimpleListItemVO;
import cn.org.sqx.mall.pojo.vo.CategorySimpleVO;
import cn.org.sqx.mall.product.service.ICategoryService;
import cn.org.sqx.mall.product.webapi.mapper.CategoryMapper;
import cn.org.sqx.mall.product.webapi.repository.ICategoryRedisRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @auther: sqx
 * @Date: 2022-09-12
 */
@Service
@Slf4j
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    ICategoryRedisRepository categoryRedisRepository;

    @Override
    public void addNew(CategoryAddNewDTO categoryAddNewDTO) {
        // 从参数中取出尝试添加的类别的名称
        String name = categoryAddNewDTO.getName();
        // 调用categoryMapper.getByName()方法查询
        CategorySimpleVO queryResult = categoryMapper.getByName(name);
        // 判断查询结果是否不为null
        if (queryResult != null) {
            // 是：抛出ServiceException
            throw new ServiceException(State.ERR_CATEGORY_NAME_DUPLICATE, "添加类别失败，父级类别不存在！");
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
            if (parentCategory == null) {
                // -- 是：抛出ServiceException
                throw new ServiceException(State.ERR_CATEGORY_NOT_FOUND, "添加类别失败，父级类别不存在！");
            }
            // -- 否：当前depth >>> 父级depth + 1
            depth = parentCategory.getDepth() + 1;

        }

        // 创建Category对象
        Category category = new Category();
        // 调用BeanUtils.copyProperties()将参数对象中的属性值复制到Category对象中
        BeanUtils.copyProperties(categoryAddNewDTO, category);
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
        if (insert != 1) {
            // 是：抛出ServiceException
            throw new ServiceException(State.ERR_INSERT, "添加类别失败，服务器忙（" + State.ERR_INSERT.getValue() + "），请稍后再次尝试！");
        }

        // 判断父级类别的isParent是否为0
        // 以下判断条件有部分多余，但不会报错
        if (parentId != 0 && parentCategory != null && parentCategory.getIsParent() != 0) {
            // 是：调用categoryMapper.updateIsParentById()方法，将父级类别的isParent修改为1，获取返回的受影响的行数
            insert = categoryMapper.updateIsParentById(parentId, 1);
            // 判断返回的受影响的行数是否不为1
            if (insert != 1) {
                // 是：抛出ServiceException
                throw new ServiceException(State.ERR_UPDATE, "添加类别失败，服务器忙（" + State.ERR_UPDATE.getValue() + "），请稍后再次尝试！");
            }
        }

    }

    @Override
    public CategoryDetailsVO getDetailsById(Long id) {
        // ===== 以下是原有代码，只从数据库中获取数据 =====
        // CategoryDetailsVO category = categoryMapper.getDetailsById(id);
        // if (category == null) {
        //     throw new ServiceException(State.ERR_CATEGORY_NOT_FOUND,
        //             "获取类别详情失败，尝试访问的数据不存在！");
        // }
        // return category;

        // ===== 以下是新的业务，将从Redis中获取数据 =====
        log.debug("根据id（{}）获取类别详情……", id);
        // 从repository中调用方法，根据id获取缓存的数据
        // 判断缓存中是否存在与此id对应的key
        boolean exists = categoryRedisRepository.exists(id);
        if (exists) {
            // 有：表示明确的存入过某数据，此数据可能是有效数据，也可能是null
            // -- 判断此key对应的数据是否为null
            CategoryDetailsVO cacheResult = categoryRedisRepository.getDetailsById(id);
            if (cacheResult == null) {
                // -- 是：表示明确的存入了null值，则此id对应的数据确实不存在，则抛出异常
                log.warn("在缓存中存在此id({})对应的Key，却是null值，则抛出异常", id);
                throw new ServiceException(State.ERR_CATEGORY_NOT_FOUND,
                        "获取类别详情失败，尝试访问的数据不存在！");
            } else {
                // -- 否：表示明确的存入了有效数据，则返回此数据即可
                return cacheResult;
            }
        }

        // 缓存中没有此id匹配的数据
        // 从mapper中调用方法，根据id获取数据库的数据
        log.debug("没有命中缓存，则从数据库查询数据……");
        CategoryDetailsVO dbResult = categoryMapper.getDetailsById(id);
        // 判断从数据库中获取的结果是否为null
        if (dbResult == null) {
            // 是：数据库也没有此数据，先向缓存中写入错误数据，再抛出异常
            log.warn("数据库中也无此数据（id={}），先向缓存中写入错误数据", id);
            categoryRedisRepository.saveEmptyValue(id);
            log.warn("抛出异常");
            throw new ServiceException(State.ERR_CATEGORY_NOT_FOUND, "获取类别详情失败，尝试访问的数据不存在！");
        }
        // 将从数据库中查询到的结果存入到缓存中
        log.debug("已经从数据库查询到匹配的数据，将数据存入缓存……");
        categoryRedisRepository.save(dbResult);
        // 返回查询结果
        log.debug("返回查询到数据：{}", dbResult);
        return dbResult;
    }

    @Override
    public List<CategorySimpleListItemVO> listByParentId(Long parentId) {
        return categoryMapper.listByParentId(parentId);
    }

    @Override
    public void preloadCache() {
        log.debug("删除缓存中的类别列表……");
        categoryRedisRepository.deleteList();

        log.debug("删除缓存中的各独立的类别数据……");
        categoryRedisRepository.deleteAllItem();

        log.debug("从数据库查询类别列表……");
        List<CategoryDetailsVO> list = categoryMapper.list();

        for (CategoryDetailsVO category : list) {
            log.debug("查询结果：{}", category);
            log.debug("将当前类别存入到Redis：{}", category);
            categoryRedisRepository.save(category);
        }
        log.debug("将类别列表写入到Redis……");
        categoryRedisRepository.save(list);
        log.debug("将类别列表写入到Redis完成！");
    }
}


