package cn.org.sqx.mall.pojo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @auther: sqx
 * @Date: 2022-09-12
 */
@Data
public class CategoryAddNewDTO implements Serializable {

    private String name;                //类别名称
    private Long parentId;              //父级id，如果无父级，则为0
    private String keywords;            //关键词列表
    private Integer sort;               //排序序号
    private String icon;                //图片的URL
    private Integer isParent;           //是否为父级，1=父级，0=不是父级
    private Integer isDisplay;          //是否显示在导航条中，1=启用，0=未启用
}
