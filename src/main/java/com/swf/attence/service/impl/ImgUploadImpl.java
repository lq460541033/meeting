package com.swf.attence.service.impl;

import com.swf.attence.service.ImageUpload;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author : white.hou
 * @description : 图片上传实现类
 * @date: 2019/1/15_18:12
 */
@Service
public class ImgUploadImpl implements ImageUpload {
    @Override
    public Boolean imgUpload(MultipartFile file,String path) {
        String originalFilename = file.getOriginalFilename();
        if(!file.isEmpty()){
            try{
                Files.copy(file.getInputStream(), Paths.get(path,originalFilename));
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }
}
