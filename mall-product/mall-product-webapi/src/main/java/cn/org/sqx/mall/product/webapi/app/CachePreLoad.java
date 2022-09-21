package cn.org.sqx.mall.product.webapi.app;

import cn.org.sqx.mall.product.service.ICategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @auther: sqx
 * @Date: 2022-09-21
 */
@Component
@Slf4j
public class CachePreLoad implements ApplicationRunner {
    @Autowired
    private ICategoryService categoryService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("CachePreLoad.run()");
        log.debug("准备执行缓存预热……");

        categoryService.preloadCache();

        log.debug("缓存预热完成！");
    }
}
