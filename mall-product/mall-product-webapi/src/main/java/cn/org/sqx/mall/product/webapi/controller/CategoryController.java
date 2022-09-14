package cn.org.sqx.mall.product.webapi.controller;

import cn.org.sqx.mall.common.web.JsonResult;
import cn.org.sqx.mall.pojo.dto.CategoryAddNewDTO;
import cn.org.sqx.mall.product.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
