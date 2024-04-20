package com.llj.model.pojo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * token中的载荷部分
 * @param <T>
 */
@Data
public class Payload<T> {
    private String id;
    private T userInfo;
    private Date expiration;
}