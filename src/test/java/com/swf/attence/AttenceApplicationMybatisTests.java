package com.swf.attence;

import com.swf.attence.entity.AdminMsg;
import com.swf.attence.entity.UserMsg;
import com.swf.attence.service.IAdminMsgService;
import com.swf.attence.service.IUserMsgService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AttenceApplicationMybatisTests {
    @Autowired
    private IAdminMsgService iAdminMsgService;
    @Autowired
    private IUserMsgService iUserMsgService;
    @Test
    public void contextLoads() {
       /* AdminMsg adminMsg = iAdminMsgService.selectById(1);
        System.out.println(adminMsg);*/
       /*AdminMsg adminMsg=new AdminMsg();*/
      /*  List<UserMsg> userMsgs = iUserMsgService.selectUserMsgAndDeptMsgById(1);
        System.out.println(userMsgs);
        List<UserMsg> userMsgs1 = iUserMsgService.selectUserMsgAndDeptMsgByUserid("1100");
        System.out.println(userMsgs1);
        UserMsg userMsg = iUserMsgService.selectById(1);*/
      /*  List<UserMsg> userMsgs1 = iUserMsgService.selectUserMsgAndDeptMsgByUserid("1100");
        System.out.println(userMsgs1);*/
      /*  UserMsg userMsg = iUserMsgService.selectById(1);
        System.out.println(userMsg);*/
        System.out.println(iUserMsgService.selectUserMsgAndDeptMsg());
    }

}

