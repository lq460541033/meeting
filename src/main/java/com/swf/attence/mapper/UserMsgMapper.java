package com.swf.attence.mapper;

import com.swf.attence.entity.UserMsg;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author auto-genergator
 * @since 2018-12-30
 */
public interface UserMsgMapper extends BaseMapper<UserMsg> {

    List<UserMsg> selectUserMsgAndDeptMsg();
/*    List<UserMsg> selectUserMsgAndDeptMsgById(Integer id);*/

    UserMsg selectUserMsgAndDeptMsgByUserid(String userid);
}
