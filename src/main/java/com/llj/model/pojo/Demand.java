package com.llj.model.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 需求
 */
@Data
public class Demand {
    private Long id;
    private Integer type;
    private String title;
    private String details;
    private String pictures;
    private Integer isFree;
    private BigDecimal price;
    private Integer status;  //状态：1-已保存 2-已付款 3-已发布
    @TableField(fill= FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill= FieldFill.INSERT)
    private Long createUser;
    @TableField(fill= FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @TableField(fill= FieldFill.INSERT_UPDATE)
    private Long updateUser;
}
