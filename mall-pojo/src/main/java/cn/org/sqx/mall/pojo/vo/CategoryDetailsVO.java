package cn.org.sqx.mall.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @auther: sqx
 * @Date: 2022-09-17
 */
@Data
public class CategoryDetailsVO implements Serializable {
    private Long id;
    private String name;
    private Long parentId;
    private Integer depth;
    private String keywords;
    private Integer sort;
    private String icon;
    private Integer enable;
    private Integer isParent;
    private Integer isDisplay;
}
