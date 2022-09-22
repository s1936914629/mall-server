package cn.org.sqx.mall.passport.controller;

import cn.org.sqx.mall.common.web.JsonResult;
import cn.org.sqx.mall.passport.service.IAdminService;
import cn.org.sqx.mall.pojo.dto.AdminLoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther: sqx
 * @Date: 2022-09-21
 */
@RestController
@RequestMapping(value = "/admins", produces = "application/json; charset=utf-8")
public class AdminController {

    @Autowired
    private IAdminService adminService;

    // 以下是测试访问的请求
    @GetMapping("/hello")
    @PreAuthorize("hasAuthority('/ams/admin/read')")
    public String sayHello() {
        return "hello~~~";
    }

    // http://localhost:8080/admins/login?username=root&password=123456
    @RequestMapping("/login")
    public JsonResult<String> login(AdminLoginDTO adminLoginDTO) {
        String jwt = adminService.login(adminLoginDTO);
        return JsonResult.ok(jwt);
    }

}
