package cn.org.sqx.mall.common.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类，避免组件扫描时扫描不到
 *
 * @auther: sqx
 * @Date: 2022-09-14
 */
@Configuration
@ComponentScan("cn.org.sqx.mall.common.ex.handler")
public class CsmallCommonConfiguration {

}
