package cn.org.sqx.mall.product.webapi;

import cn.org.sqx.mall.common.config.CsmallCommonConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @auther: sqx
 * @Date: 2022-09-11
 */
@SpringBootApplication
@Import({CsmallCommonConfiguration.class}) // 新增
public class MallProductWebapiApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallProductWebapiApplication.class, args);
    }
}
