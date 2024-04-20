package com.llj.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.llj.common.BaseContext;
import com.llj.common.CustomException;
import com.llj.common.R;
import com.llj.model.dto.OrderDto;
import com.llj.model.pojo.Orders;
import com.llj.service.IOrderRemarkService;
import com.llj.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;
    @Autowired
    private IOrderRemarkService remarkService;

    /**
     * 志愿者确认订单
     * @param orderId
     * @return
     */
    @PostMapping("confirm")
    public R<String> confirm(Long orderId){
        OrderDto order = orderService.getById(orderId);
        if(order.getStatus()!=1){
            throw new CustomException("创建后才可以确认！");
        }
        UpdateWrapper<Orders> uw = new UpdateWrapper<Orders>().eq("id",orderId).set("status",2).set("server_id", BaseContext.getCurrentId());
        orderService.update(uw);
        return R.success("确认成功！");
    }

    /**
     * 开始订单对应服务
     * @param orderId
     * @return
     */
    @PostMapping("begin")
    public R<String> begin(Long orderId){
        OrderDto order = orderService.getById(orderId);
        if(order.getStatus() != 2){
            throw new CustomException("确认后才可以开始！");
        }
        UpdateWrapper<Orders> uw = new UpdateWrapper<Orders>().eq("id",orderId).set("status",3);
        orderService.update(uw);
        return R.success("已开始！");
    }

    /**
     * 结束订单
     * @param orderId
     * @return
     */
    @PostMapping("end")
    public R<String> end(Long orderId){
        OrderDto order = orderService.getById(orderId);
        if(order.getStatus()!=3){
            throw new CustomException("开始后才可以结束！");
        }
        UpdateWrapper<Orders> uw = new UpdateWrapper<Orders>().eq("id",orderId).set("status",4);
        orderService.update(uw);
        return R.success("已结束！");
    }

    /**
     * 评价订单的服务
     * @param remark
     * @return
     */
    @PostMapping("remark")
    public R<String> remark(@RequestBody Orders.Remark remark){
        OrderDto order = orderService.getById(remark.getOrderId());
        if(order.getStatus()!=4){
            throw new CustomException("结束后才可以评价！");
        }
        remarkService.save(remark);
        UpdateWrapper<Orders> uw = new UpdateWrapper<Orders>().eq("id",remark.getOrderId()).set("status",5);
        orderService.update(uw);
        return R.success("评论成功！");
    }

    /**
     * 对当前订单报警
     * @param orderId
     * @return
     */
    @PostMapping("warn")
    public R<String> warn(Long orderId){
        UpdateWrapper<Orders> uw = new UpdateWrapper<Orders>().eq("id",orderId).set("status",6);
        orderService.update(uw);
        return R.success("已报警！");
    }

    @GetMapping("{orderId}")
    public R<OrderDto> queryById(@PathVariable Long orderId){
        OrderDto orderDto = orderService.getById(orderId);
        return R.success(orderDto);
    }

    @GetMapping("page")
    public R<IPage> queryByPageAndCondition(String keyword,int status,int start,int pageSize){
        IPage<OrderDto> page = orderService.queryByAndCondition(keyword,status,start,pageSize);
        return R.success(page);
    }

}
