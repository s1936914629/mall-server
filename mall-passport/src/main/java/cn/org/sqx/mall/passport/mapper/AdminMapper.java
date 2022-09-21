package cn.org.sqx.mall.passport.mapper;

import cn.org.sqx.mall.pojo.vo.AdminLoginVO;

/**
 * @auther: sqx
 * @Date: 2022-09-21
 */
public interface AdminMapper {

    /**
     * 根据用户名查询管理员的登录信息
     *
     * @param username 用户名
     * @return 匹配的管理员的登录信息，如果没有匹配的数据，则返回null
     */
    AdminLoginVO getLoginInfoByUsername(String username);
}
