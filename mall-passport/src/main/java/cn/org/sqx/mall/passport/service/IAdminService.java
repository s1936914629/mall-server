package cn.org.sqx.mall.passport.service;

import cn.org.sqx.mall.pojo.dto.AdminLoginDTO;
import org.springframework.stereotype.Service;

/**
 * @auther: sqx
 * @Date: 2022-09-21
 */
public interface IAdminService {
    String login(AdminLoginDTO adminLoginDTO);
}
