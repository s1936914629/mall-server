package cn.org.sqx.mall.passport.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @auther: sqx
 * @Date: 2022-09-21
 */
@Configuration
@MapperScan("cn.org.sqx.mall.passport.mapper")
public class MybatisConfiguration {
}
