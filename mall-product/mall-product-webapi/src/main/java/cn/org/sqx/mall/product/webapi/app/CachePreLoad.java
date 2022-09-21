package cn.org.sqx.mall.product.webapi.app;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @auther: sqx
 * @Date: 2022-09-21
 */
@Component
public class CachePreLoad implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("char.run()");
    }
}
