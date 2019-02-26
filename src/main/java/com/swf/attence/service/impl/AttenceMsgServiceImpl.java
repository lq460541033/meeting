package com.swf.attence.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.swf.attence.entity.AttenceMsg;
import com.swf.attence.mapper.AttenceMsgMapper;
import com.swf.attence.service.IAttenceMsgService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author auto-genergator
 * @since 2018-12-30
 */
@Service
public class AttenceMsgServiceImpl extends ServiceImpl<AttenceMsgMapper, AttenceMsg> implements IAttenceMsgService {
    @Autowired
   private AttenceMsgMapper attenceMsgMapper;



 /*   @Override
    public boolean generateReport(String day, MultipartFile file) throws IOException {
        InputStream fileInputStream = file.getInputStream();
        XSSFWorkbook sheets = new XSSFWorkbook(fileInputStream);
        *//**
         * 得到excel 工作表
         *//*
        XSSFSheet sheet = sheets.getSheetAt(0);

        return false;
    }*/

    @Override
    public boolean attenceMsgExist(AttenceMsg attenceMsg) {
        AttenceMsg attenceMsg1 = attenceMsgMapper.selectOne(attenceMsg);
        return attenceMsg1 != null;

    }


}
