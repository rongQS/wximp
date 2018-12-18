package com.platform.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.platform.annotation.SysLog;
import com.platform.entity.SysSellerEntity;
import com.platform.service.SysSellerService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
import com.platform.validator.ValidatorUtils;
import com.platform.validator.group.AddGroup;

/**
 * Controller
 *
 * @author qins
 * @email 506657803@qq.com
 * @date 2018-10-31 21:53:48
 */
@RestController
@RequestMapping("sys/seller")
public class SysSellerController extends AbstractController {
    @Autowired
    private SysSellerService sysSellerService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:seller:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<SysSellerEntity> sellerList = sysSellerService.queryList(query);
        int total = sysSellerService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(sellerList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:seller:info")
    public R info(@PathVariable("id") Long id) {
        SysSellerEntity seller = sysSellerService.queryObject(id);
        return R.ok().put("seller", seller);
    }

    /**
     * 保存
     */
    @SysLog("保存商户信息")
    @RequestMapping("/save")
    @RequiresPermissions("sys:seller:save")
    public R save(@RequestBody SysSellerEntity seller) {
    	ValidatorUtils.validateEntity(seller, AddGroup.class);
        sysSellerService.save(seller);

        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("修改商户信息")
    @RequestMapping("/update")
    @RequiresPermissions("sys:seller:update")
    public R update(@RequestBody SysSellerEntity seller) {
        sysSellerService.update(seller);
        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除商户信息")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:seller:delete")
    public R delete(@RequestBody Long[]ids) {
        sysSellerService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<SysSellerEntity> list = sysSellerService.queryList(params);
        return R.ok().put("list", list);
    }
}
