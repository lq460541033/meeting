package com.swf.attence.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.swf.attence.config.MyException;
import com.swf.attence.entity.DeptMsg;
import com.swf.attence.entity.UserMsg;
import com.swf.attence.mapper.DeptMsgMapper;
import com.swf.attence.mapper.UserMsgMapper;
import com.swf.attence.service.IUserMsgService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.swf.attence.service.ImageUpload;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.internal.ContentType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.*;

import static javax.swing.text.DefaultStyledDocument.ElementSpec.ContentType;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author auto-genergator
 * @since 2018-12-30
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserMsgServiceImpl extends ServiceImpl<UserMsgMapper, UserMsg> implements IUserMsgService {

    @Autowired
    private UserMsgMapper userMsgMapper;
    @Autowired
    private DeptMsgMapper deptMsgMapper;
    @Autowired
    private ImageUpload imageUpload;

    private static final String PATH = "F:\\Attence相关\\userpic\\";

    @Override
    public List selectUserMsgAndDeptMsg() {
        List<UserMsg> userMsgs = userMsgMapper.selectUserMsgAndDeptMsg();
        System.out.println("方法selectUserMsgAndDeptMsg查询到的list是： " + userMsgs);
        return userMsgs;
    }

    @Override
    public List selectUserMsgAndDeptMsgById(Integer id) {
        return null;
    }

    @Override
    public List selectUserMsgAndDeptMsgByUserid(String userid) {
        return null;
    }

    @Override
    public void delImgFromUserpic(Integer id) {
        UserMsg userMsg = userMsgMapper.selectById(id);
        String userid = userMsg.getUserid();
        File file = new File(PATH);
        String realPath = null;
        File[] files = file.listFiles();
        for (File f : files
                ) {
            String name = f.getName();
            if (name.endsWith("jpg")) {
                realPath = PATH + userid + ".jpg";
                File file1 = new File(realPath);
                file1.delete();

            } else if (name.endsWith("png")) {
                realPath = PATH + userid + ".png";
                File file1 = new File(realPath);
                file1.delete();
            } else if (name.endsWith("jpeg")) {
                realPath = PATH + userid + ".jpeg";
                File file1 = new File(realPath);
                file1.delete();

            }
        }
    }

    @Override
    public Boolean insertIntoDatebase(String fileName, MultipartFile file) throws Exception {
        boolean notNull = false;
        List<UserMsg> userMsgs = new ArrayList<>();
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            throw new MyException("上传文件格式不正确");
        }
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        InputStream is = file.getInputStream();
        Workbook wb = null;
        if (isExcel2003) {
            wb = new HSSFWorkbook(is);
        } else {
            wb = new XSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(0);
        if (sheet != null) {
            notNull = true;
        }
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            if (row.getCell(1).getCellType() != 1) {
                throw new MyException("导入失败(第" + (i + 1) + "行,姓名请设为文本格式)");
            }
            /**
             * 用户名
             */
            row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
            String userid = row.getCell(0).getStringCellValue();
            UserMsg userMsg = userMsgMapper.selectUserMsgAndDeptMsgByUserid(userid);
            if (userid == null || userid.isEmpty()) {
                throw new MyException("导入失败（第" + (i + 1) + "行，用户工号未填写)");
            } else if (userMsg!=null) {
                throw new MyException("导入失败（第" + (i + 1) + "行，该用户已存在)");
            }
            /**
             * 用户姓名
             */
            String username = row.getCell(1).getStringCellValue();
            if (username == null || username.isEmpty()) {
                throw new MyException("导入失败（第" + (i + 1) + "行，用户姓名未填写)");
            }
            /**
             * 性别
             */
            String gender = row.getCell(2).getStringCellValue();
            Integer realGender;
            if (gender == null || gender.isEmpty()) {
                throw new MyException("导入失败（第" + (i + 1) + "行，用户性别未填写)");
            } else {
                String gender1 = "男";
                String gender0 = "女";
                if (gender1.equals(gender)) {
                    realGender = 1;
                } else if (gender0.equals(gender)) {
                    realGender = 0;
                } else {
                    throw new MyException("导入失败（第" + (i + 1) + "行，用户性别只能为男/女)");
                }
            }
            /**
             * 部门
             */
            String deptid = row.getCell(3).getStringCellValue();
            String realDeptid;
            if (deptid == null || deptid.isEmpty()) {
                throw new MyException("导入失败（第" + (i + 1) + "行，用户部门未填写)");
            } else {
                List<DeptMsg> deptname = deptMsgMapper.selectList(new EntityWrapper<DeptMsg>().eq("deptname", deptid));
                DeptMsg deptMsg = deptname.get(0);
                realDeptid = deptMsg.getDeptid();
            }
            /**
             * 图片路径
             */
            String userpic = row.getCell(4).getStringCellValue();
            if (userpic == null || userpic.isEmpty()) {
                throw new MyException("导入失败（第" + (i + 1) + "行，用户照片路径未填写)");
            } else {
                if (!userpic.matches("^.+\\.(?i)(jpg)$") && !userpic.matches("^.+\\.(?i)(jpeg)$") && !userpic.matches("^.+\\.(?i)(png)$")) {
                    throw new MyException("导入失败（第" + (i + 1) + "行，用户照片路径填写错误，格式应为jpg、jpeg、png)");
                } else if (!userpic.contains(userid)) {
                    throw new MyException("导入失败（第" + (i + 1) + "行，用户照片路径填写错误,照片名应和工号一致)");
                }
            }
            UserMsg userMsg1 = new UserMsg();
            userMsg1.setUserid(userid);
            userMsg1.setUsername(username);
            userMsg1.setDeptid(realDeptid);
            userMsg1.setGender(realGender);
            try {
                File file1 = new File(userpic);
                FileInputStream fileInputStream = new FileInputStream(file1);
                String name = file1.getName();
                MockMultipartFile mockMultipartFile = new MockMultipartFile(name,fileInputStream);
                if (imageUpload.fileUpload(mockMultipartFile, PATH)) {
                    userMsg1.setUserpic(name);
                    userMsgs.add(userMsg1);
                    userMsgMapper.insert(userMsg1);
                    notNull=true;
                } else {
                    throw new MyException("（第" + (i + 1) + "行用户照片导入失败");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return notNull;
    }
}
