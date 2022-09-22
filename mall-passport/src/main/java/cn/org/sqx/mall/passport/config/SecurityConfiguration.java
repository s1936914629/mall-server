package cn.org.sqx.mall.passport.config;

import cn.org.sqx.mall.passport.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @auther: sqx
 * @Date: 2022-09-21
 */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    // 新增
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 禁用防跨域攻击
        http.csrf().disable();

        // URL白名单
        String[] urls = {
                "/admins/login",
                "/doc.html",  // 从本行开始，以下是新增
                "/**/*.js",
                "/**/*.css",
                "/swagger-resources",
                "/v2/api-docs",
                "/favicon.ico"
        };

        // 配置各请求路径的认证与授权
        http.authorizeRequests()    // 请求需要授权才可以访问
                .antMatchers(urls)  // 匹配一些路径
                .permitAll()        // 允许直接访问（不需要经过认证和授权）
                .anyRequest()       // 匹配处理以上配置的其他请求
                .authenticated();   // 都需要认证

        // 注册处理JWT的过滤器
        // 此过滤器必须在Spring Security处理登录的过滤器之前
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}

