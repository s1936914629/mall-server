package cn.org.sqx.mall.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @auther: sqx
 * @Date: 2022-09-11
 */
@Data
public class Category implements Serializable {

    private Long id;
    private String name;                //类别名称
    private Long parentId;              //父级id，如果无父级，则为0
    private Integer depth;              //深度，顶级为1，次级为2，依次类推
    private String keywords;            //关键词列表
    private Integer sort;               //排序序号
    private String icon;                //图片的URL
    private Integer enable;             //是否启用，1=启用，0=未启用
    private Integer isParent;           //是否为父级，1=父级，0=不是父级
    private Integer isDisplay;          //是否显示在导航条中，1=启用，0=未启用
    private LocalDateTime gmtCreate;    //数据创建时间
    private LocalDateTime gmtModified;  //数据最后修改时间


}
