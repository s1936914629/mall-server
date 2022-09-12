package cn.org.sqx.mall.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @auther: sqx
 * @Date: 2022-09-12
 */
@Data
public class CategorySimpleVO implements Serializable {

    private Long id;
    private Integer depth;
    private Integer isParent;

}
