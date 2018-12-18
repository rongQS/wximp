package com.platform.oss;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class LocalCloudStorageService extends CloudStorageService{

    public LocalCloudStorageService(CloudStorageConfig config) {
        this.config = config;
        //初始化
        init();
    }

    private void init(){
    }

	@Override
	public String upload(byte[] data, String path) {
		try {
//			String m_path = Path.class.getResource("/").getPath();
			String m_path = getUserfilesBaseDir();
			String filePath = m_path + path;
			File file = new File(filePath);
			if(!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
	        if(!file.exists()){
	            file.createNewFile();
	        }
			FileCopyUtils.copy(data, new File(filePath));
		} catch (IOException e) {
			e.printStackTrace();
			path = "";
		}
		return config.getLocalDomain()+ "/fileservice/" + path;
	}

	@Override
	public String upload(InputStream inputStream, String path) {
		try {
			byte[] data = IOUtils.toByteArray(inputStream);
			path = this.upload(data, path);
		} catch (IOException e) {
			e.printStackTrace();
			path = "";
		}
		
		return path;
	}

	@Override
	public String upload(MultipartFile file) throws Exception {
		String fileName = file.getOriginalFilename();
        String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);
        return upload(file.getBytes(), getPath(config.getLocalPrefix()) + "." + prefix);
	}
}
