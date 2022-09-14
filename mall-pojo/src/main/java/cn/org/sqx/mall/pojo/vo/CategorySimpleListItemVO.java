package cn.org.sqx.mall.pojo.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @auther: sqx
 * @Date: 2022-09-14
 */
@Data
public class CategorySimpleListItemVO {
    private Long id;
    private String name;                //类别名称
    private Integer sort;               //排序序号
    private String icon;                //图片的URL
    private Integer isParent;           //是否为父级，1=父级，0=不是父级

}
