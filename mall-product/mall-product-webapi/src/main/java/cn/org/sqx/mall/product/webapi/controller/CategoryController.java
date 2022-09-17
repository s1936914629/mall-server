package cn.org.sqx.mall.product.webapi.controller;

import cn.org.sqx.mall.common.web.JsonResult;
import cn.org.sqx.mall.pojo.dto.CategoryAddNewDTO;
import cn.org.sqx.mall.pojo.vo.CategoryDetailsVO;
import cn.org.sqx.mall.pojo.vo.CategorySimpleListItemVO;
import cn.org.sqx.mall.product.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @auther: sqx
 * @Date: 2022-09-14
 */
@RestController
@RequestMapping(value = "/categories",produces = "application/json; charset=utf-8")
public class CategoryController {

    @Autowired
    private ICategoryService iCategoryService;

    @PostMapping("/add-new")
    // ===== 在以下方法的参数前添加@Validated / @Valid注解 =====
    public JsonResult<Void> addNew(@Validated CategoryAddNewDTO categoryAddNewDTO) {
        iCategoryService.addNew(categoryAddNewDTO);
        return JsonResult.ok();
    }

    @GetMapping("/{id}")
    public JsonResult<CategoryDetailsVO> getDetailsById(@PathVariable Long id){
        CategoryDetailsVO category = iCategoryService.getDetailsById(id);
        return JsonResult.ok(category);

    }

    @GetMapping("/list-by-parent")
    public JsonResult<List<CategorySimpleListItemVO>> listByParentId(Long parentId) {
        // 调用service并将结果封装到JsonResult中
        List<CategorySimpleListItemVO> list = iCategoryService.listByParentId(parentId);
        return JsonResult.ok(list);
    }

}
