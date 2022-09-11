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
    private String name;
    private Long parentId;
    private Integer depth;
    private String keywords;
    private Integer sort;
    private String icon;
    private Integer enable;
    private Integer isParent;
    private Integer isDisplay;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;


}
