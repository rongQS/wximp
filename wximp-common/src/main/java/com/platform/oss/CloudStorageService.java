package com.platform.oss;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.platform.utils.DateUtils;

/**
 * 云存储(支持七牛、阿里云、腾讯云、又拍云)
 *
 * @author qins
 * @email 506657803@qq.com
 * @date 2017-03-25 14:58
 */
public abstract class CloudStorageService {
    /**
     * 云存储配置信息
     */
    CloudStorageConfig config;

    /**
     * 文件路径
     *
     * @param prefix 前缀
     * @return 返回上传路径
     */
    public String getPath(String prefix) {
        //生成uuid
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        //文件路径
        String path = DateUtils.format(new Date(), "yyyyMMdd") + "/" + DateUtils.format(new Date(), "HHmmssS") + uuid.substring(0, 5);

        if (StringUtils.isNotBlank(prefix)) {
            path = prefix + "/" + path;
        }

        return path;
    }
    
	public static String getUserfilesBaseDir() {
		String dir = "/Users/qinsong/fileservice";
		if (StringUtils.isBlank(dir)){
			try {
				dir = Path.class.getResource("/").getPath();
			} catch (Exception e) {
				return "";
			}
		}
		if(!dir.endsWith("/")) {
			dir += "/";
		}
		return dir;
	}

    /**
     * 文件上传
     *
     * @param file 文件
     * @return 返回http地址
     */
    public abstract String upload(MultipartFile file) throws Exception;

    /**
     * 文件上传
     *
     * @param data 文件字节数组
     * @param path 文件路径，包含文件名
     * @return 返回http地址
     */
    public abstract String upload(byte[] data, String path);

    /**
     * 文件上传
     *
     * @param inputStream 字节流
     * @param path        文件路径，包含文件名
     * @return 返回http地址
     */
    public abstract String upload(InputStream inputStream, String path);

}
