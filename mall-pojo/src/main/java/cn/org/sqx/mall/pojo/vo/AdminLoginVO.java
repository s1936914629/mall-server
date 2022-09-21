package cn.org.sqx.mall.pojo.vo;

import lombok.Data;

import java.util.List;

/**
 * @auther: sqx
 * @Date: 2022-09-21
 */
@Data
public class AdminLoginVO {
    private Long id;
    private String username;           //用户名
    private String password;           //密码
    private Integer isEnable;          //是否启用
    private List<String> permissions;  //权限列表
}
