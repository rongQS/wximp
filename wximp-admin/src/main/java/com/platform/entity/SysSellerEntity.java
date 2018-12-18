package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

import com.platform.validator.group.AddGroup;
import com.platform.validator.group.UpdateGroup;


/**
 * 实体
 * 表名 sys_seller
 *
 * @author qins
 * @email 506657803@qq.com
 * @date 2018-10-31 21:53:48
 */
public class SysSellerEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Long id;
    //商户名称
    @NotBlank(message = "商户名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String name;
    //商户号
    @NotBlank(message = "商户号不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String code;
    //商户门头
    private String logo;
    //详细地址
    private String address;
    //微信设备号
    @NotBlank(message = "微信id不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String appid;
    //微信密钥
    @NotBlank(message = "微信密钥不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String appsecret;
    //微信号
    @NotBlank(message = "商户微信号不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String originalid;
    //商户状态：0.正常，1.停用，2删除
    private String status;
    //创建时间
    private Date createTime;
    //创建者
    private Long createUserId;

  //经度
    private String longitude;
  //维度
    private String latitude;

    /**
     * 设置：
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：
     */
    public Long getId() {
        return id;
    }
    /**
     * 设置：商户名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：商户名称
     */
    public String getName() {
        return name;
    }
    /**
     * 设置：商户号
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取：商户号
     */
    public String getCode() {
        return code;
    }
    /**
     * 设置：商户门头
     */
    public void setLogo(String logo) {
        this.logo = logo;
    }

    /**
     * 获取：商户门头
     */
    public String getLogo() {
        return logo;
    }
    
    public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	/**
     * 设置：微信设备号
     */
    public void setAppid(String appid) {
        this.appid = appid;
    }

    /**
     * 获取：微信设备号
     */
    public String getAppid() {
        return appid;
    }
    /**
     * 设置：微信密钥
     */
    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }

    /**
     * 获取：微信密钥
     */
    public String getAppsecret() {
        return appsecret;
    }
    /**
     * 设置：微信号
     */
    public void setOriginalid(String originalid) {
        this.originalid = originalid;
    }

    /**
     * 获取：微信号
     */
    public String getOriginalid() {
        return originalid;
    }
    /**
     * 设置：商户状态：0.正常，1.停用，2删除
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取：商户状态：0.正常，1.停用，2删除
     */
    public String getStatus() {
        return status;
    }
    /**
     * 设置：创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取：创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }
    /**
     * 设置：创建者
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 获取：创建者
     */
    public Long getCreateUserId() {
        return createUserId;
    }

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
}
