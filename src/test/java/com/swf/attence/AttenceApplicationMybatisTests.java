package com.swf.attence;

import com.swf.attence.entity.AdminMsg;
import com.swf.attence.entity.AttenceMsg;
import com.swf.attence.entity.ICommand;
import com.swf.attence.entity.UserMsg;
import com.swf.attence.mapper.ICommandMapper;
import com.swf.attence.service.IAdminMsgService;
import com.swf.attence.service.IEveryTaskService;
import com.swf.attence.service.IUserMsgService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AttenceApplicationMybatisTests {
    @Autowired
    private IAdminMsgService iAdminMsgService;
    @Autowired
    private IUserMsgService iUserMsgService;
    @Autowired
    private IEveryTaskService iEveryTaskService;
    @Autowired
    private ICommandMapper iCommandMapper;
    @Test
    public void contextLoads() {
        UserMsg userMsg = new UserMsg();
        userMsg.setUserid("10101012");
        userMsg.setGender(1);
        userMsg.setUserpic("C:\\Users\\White.Hou\\Desktop\\10101012.jpg");
        userMsg.setDeptid("1");
        userMsg.setUsername("ceshi");
        iUserMsgService.insert(userMsg);
    }

}

