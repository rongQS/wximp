package com.platform.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.dao.SysSellerDao;
import com.platform.entity.SysSellerEntity;
import com.platform.service.SysSellerService;
import com.platform.utils.ShiroUtils;

/**
 * Service实现类
 *
 * @author qins
 * @email 506657803@qq.com
 * @date 2018-10-31 21:53:48
 */
@Service("sysSellerService")
public class SysSellerServiceImpl implements SysSellerService {
    @Autowired
    private SysSellerDao sysSellerDao;

    @Override
    public SysSellerEntity queryObject(Long id) {
        return sysSellerDao.queryObject(id);
    }

    @Override
    public List<SysSellerEntity> queryList(Map<String, Object> map) {
        return sysSellerDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return sysSellerDao.queryTotal(map);
    }

    @Override
    public int save(SysSellerEntity seller) {
    	seller.setCreateUserId(ShiroUtils.getUserId());
    	seller.setCreateTime(new Date());
    	seller.setStatus("0");
        return sysSellerDao.save(seller);
    }

    @Override
    public int update(SysSellerEntity seller) {
        return sysSellerDao.update(seller);
    }

    @Override
    public int delete(Long id) {
        return sysSellerDao.delete(id);
    }

    @Override
    public int deleteBatch(Long[]ids) {
        return sysSellerDao.deleteBatch(ids);
    }
}
