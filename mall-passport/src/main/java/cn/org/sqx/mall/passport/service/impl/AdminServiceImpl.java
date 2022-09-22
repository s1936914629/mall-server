package cn.org.sqx.mall.passport.service.impl;

import cn.org.sqx.mall.passport.service.IAdminService;
import cn.org.sqx.mall.pojo.dto.AdminLoginDTO;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @auther: sqx
 * @Date: 2022-09-21
 */
@Service
public class AdminServiceImpl implements IAdminService {
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * JWT数据的密钥
     */
    private String secretKey = "mjqhrgx";

    @Override
    public String login(AdminLoginDTO adminLoginDTO) {
        // 准备被认证数据
        Authentication authentication = new UsernamePasswordAuthenticationToken(adminLoginDTO.getUsername(), adminLoginDTO.getPassword());

        // 调用AuthenticationManager验证用户名与密码
        // 执行认证，如果此过程没有抛出异常，则表示认证通过，如果认证信息有误，将抛出异常
        Authentication authenticate = authenticationManager.authenticate(authentication);

        // 如果程序可以执行到此处，则表示登录成功
        // 生成此用户数据的JWT
        // Claims
        User user = (User) authenticate.getPrincipal();
        System.out.println("从认证结果中获取Principal=" + user.getClass().getName());
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());
        claims.put("permissions", user.getAuthorities());
        System.out.println("即将向JWT中写入数据=" + claims);

        // JWT的组成部分：Header（头），Payload（载荷），Signature（签名）
        String jwt = Jwts.builder()
                // Header：指定算法与当前数据类型
                // 格式为： { "alg": 算法, "typ": "jwt" }
                .setHeaderParam(Header.CONTENT_TYPE, "HS256")
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                // Payload：通常包含Claims（自定义数据）和过期时间
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + 5 * 60 * 1000))
                // Signature：由算法和密钥（secret key）这2部分组成
                .signWith(SignatureAlgorithm.HS256, secretKey)
                // 打包生成
                .compact();

        // 返回JWT数据
        return jwt;

    }
}
