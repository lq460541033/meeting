package com.swf.attence.service;

import com.swf.attence.entity.AttenceMsg;
import com.baomidou.mybatisplus.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author auto-genergator
 * @since 2018-12-30
 */
public interface IAttenceMsgService extends IService<AttenceMsg> {
    /**
     * 添加的用户是否存在
     * @param attenceMsg
     * @return
     */
  boolean attenceMsgExist(AttenceMsg attenceMsg);



    /**
     * 将考勤数据写入已生成的报表中
     * @param day
     * @param file
     * @return
     */
 /* boolean generateReport(String day,MultipartFile file) throws IOException;*/
}
