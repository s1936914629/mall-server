package cn.org.sqx.mall.passport.service.impl;

import cn.org.sqx.mall.passport.service.IAdminService;
import cn.org.sqx.mall.pojo.dto.AdminLoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * @auther: sqx
 * @Date: 2022-09-21
 */
@Service
public class AdminServiceImpl implements IAdminService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public String login(AdminLoginDTO adminLoginDTO) {
        // 准备被认证数据
        Authentication authentication = new UsernamePasswordAuthenticationToken(adminLoginDTO.getUsername(), adminLoginDTO.getPassword());

        // 调用AuthenticationManager验证用户名与密码
        // 执行认证，如果此过程没有抛出异常，则表示认证通过，如果认证信息有误，将抛出异常
        authenticationManager.authenticate(authentication);

        // 如果程序可以执行到此处，则表示登录成功
        // 生成此用户数据的JWT
        String jwt = "This is a JWT."; // 临时
        return jwt;

    }
}
