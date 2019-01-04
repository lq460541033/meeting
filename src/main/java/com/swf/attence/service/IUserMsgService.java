package com.swf.attence.service;

import com.swf.attence.entity.UserMsg;
import com.baomidou.mybatisplus.service.IService;

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

}
