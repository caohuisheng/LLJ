package com.llj.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.llj.common.BaseContext;
import com.llj.common.R;
import com.llj.model.pojo.Message;
import com.llj.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private IMessageService messageService;

    /**
     * 添加消息
     * @param message
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody Message message){
        message.setMilliSeconds(System.currentTimeMillis());
        messageService.save(message);
        return R.success("添加成功！");
    }

    /**
     * 删除消息
     * @param messageId
     * @return
     */
    @DeleteMapping("{messageId}")
    public R<String> delete(@PathVariable Long messageId){
        messageService.removeById(messageId);
        return R.success("删除成功！");
    }

    /**
     * 设置消息已读
     * @param messageId
     * @return
     */
    @PostMapping("read")
    public R<String> isRead(Long messageId){
        UpdateWrapper<Message> uw = new UpdateWrapper<Message>().eq("id",messageId).set("is_readed",1);
        messageService.update(uw);
        return R.success("设置成功！");
    }

    /**
     * 查询最近消息
     * @param beginTime
     * @return
     */
    @GetMapping("recent")
    public R<Object> getRecentMessage(Long beginTime){
        QueryWrapper<Message> qw = new QueryWrapper<Message>()
                .gt("milli_seconds",beginTime)
                .eq("create_user", BaseContext.getCurrentId())
                .or().eq("receiver_id",BaseContext.getCurrentId())
                .orderByAsc("milli_seconds");
        List<Message> results = messageService.list(qw);
        return R.success(results);
    }
}
