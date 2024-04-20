package com.llj.model.dto;

import com.llj.model.pojo.Orders;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDto extends Orders {
    private String title;
    private String details;
    private String pictures;
    private Integer isFree;
    private BigDecimal price;
}
