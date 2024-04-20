package com.llj.model.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 订单
 */
@Data
public class Orders {
    private Long id;
    private Long demandId;
    private Integer status; //1-已创建 2-已确认 3-进行中 4-已结束 5-已评价 6-异常
    private Long serverId;
    @TableField(fill= FieldFill.INSERT)
    private Long createUser;
    @TableField(fill= FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill= FieldFill.INSERT)
    private Long updateUser;
    @TableField(fill= FieldFill.INSERT)
    private LocalDateTime updateTime;

    @Data
    @TableName("order_remark")
    public static class Remark{
        private Long id;
        private Long orderId;
        private String content;
        private Integer suggestRating;
        private Integer globalRating;
        @TableField(fill= FieldFill.INSERT)
        private Long createUser;
        @TableField(fill= FieldFill.INSERT)
        private LocalDateTime createTime;
    }
}
