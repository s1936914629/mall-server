package cn.org.sqx.mall.pojo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @auther: sqx
 * @Date: 2022-09-21
 */
@Data
public class AdminLoginDTO implements Serializable {
    private String username;
    private String password;
}
