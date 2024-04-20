package com.llj.model.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 打卡
 */
@Data
public class Sign {
    private Long id;
    private Integer type;
    private String content;
    private String pictures;
    @TableField(fill=FieldFill.INSERT)
    private Long createUser;
    @TableField(fill=FieldFill.INSERT)
    private LocalDateTime createTime;

    @Data
    @TableName("sign_remark")
    public static class Remark{
        private Long id;
        private Long signId;
        private String content;
        @TableField(fill=FieldFill.INSERT)
        private Long createUser;
        @TableField(fill=FieldFill.INSERT)
        private LocalDateTime createTime;
    }

    @Data
    @TableName("sign_liker")
    public static class Liker{
        private Long id;
        private Long signId;
        @TableField(fill=FieldFill.INSERT)
        private Long createUser;
        @TableField(fill=FieldFill.INSERT)
        private LocalDateTime createTime;
    }
}
