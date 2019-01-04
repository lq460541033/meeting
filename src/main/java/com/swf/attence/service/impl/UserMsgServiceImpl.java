package com.swf.attence.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.swf.attence.entity.DeptMsg;
import com.swf.attence.entity.UserMsg;
import com.swf.attence.mapper.DeptMsgMapper;
import com.swf.attence.mapper.UserMsgMapper;
import com.swf.attence.service.IUserMsgService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author auto-genergator
 * @since 2018-12-30
 */
@Service
public class UserMsgServiceImpl extends ServiceImpl<UserMsgMapper, UserMsg> implements IUserMsgService {

    @Autowired
    private  UserMsgMapper userMsgMapper;
    @Override
    public List selectUserMsgAndDeptMsg() {
        List<UserMsg> userMsgs = userMsgMapper.selectUserMsgAndDeptMsg();
        System.out.println("方法selectUserMsgAndDeptMsg查询到的list是： "+userMsgs);
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
}
