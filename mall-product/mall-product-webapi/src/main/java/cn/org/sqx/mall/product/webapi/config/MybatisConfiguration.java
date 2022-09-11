package cn.org.sqx.mall.product.webapi.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @auther: sqx
 * @Date: 2022-09-11
 */
@Configuration
@MapperScan("cn.org.sqx.mall.product.webapi.mapper")
public class MybatisConfiguration {

}
