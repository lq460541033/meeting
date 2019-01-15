package com.swf.attence.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author : white.hou
 * @description : 图片上传接口
 * @date: 2019/1/15_18:10
 */
public interface ImageUpload {
    /**
     * 上传图片
     * @param file
     * @param path
     * @return
     */
    Boolean imgUpload(MultipartFile file,String path);
}
