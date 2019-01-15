package com.swf.attence.service;

import com.swf.attence.entity.UserMsg;
import com.baomidou.mybatisplus.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author auto-genergator
 * @since 2018-12-30
 */
public interface IUserMsgService extends IService<UserMsg> {

    /**
     * 查
     * @return
     */
    List selectUserMsgAndDeptMsg();
    /**
     * 通过指定id查询用户的所有信息
     * @param id
     * @return
     */
     List selectUserMsgAndDeptMsgById(Integer id);
    /**
     * 通过指定id查询用户的所有信息
     * @param userid
     * @return
     */
    List selectUserMsgAndDeptMsgByUserid(String userid);

    /**
     * 根据用户工号删除图片文件夹指定员工的图片
     * @param id
     */
    void delImgFromUserpic(Integer id);

    /**
     * 上传excel
     * @param fileName
     * @param file
     * @return
     * @throws Exception
     */
    Boolean insertIntoDatebase(String fileName, MultipartFile file) throws  Exception;
}
