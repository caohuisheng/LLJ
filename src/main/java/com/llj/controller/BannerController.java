package com.llj.controller;

import com.llj.common.R;
import com.llj.model.pojo.Banner;
import com.llj.service.IBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/banner")
public class BannerController {

    @Autowired
    private IBannerService bannerService;

    @PostMapping
    public R<String> save(@RequestBody Banner banner){
        bannerService.save(banner);
        return R.success("添加成功！");
    }

    @PutMapping
    public R<String> update(@RequestBody Banner banner){
        bannerService.updateById(banner);
        return R.success("修改成功！");
    }

    @DeleteMapping("{bannerId}")
    public R<String> delete(@PathVariable Long bannerId){
        bannerService.removeById(bannerId);
        return R.success("删除成功！");
    }

    @GetMapping("{bannerId}")
    public R<Banner> queryById(@PathVariable Long bannerId){
        Banner banner = bannerService.getById(bannerId);
        return R.success(banner);
    }

    @GetMapping("list")
    public R<List<Banner>> list(){
        List<Banner> list = bannerService.list();
        return R.success(list);
    }
}
