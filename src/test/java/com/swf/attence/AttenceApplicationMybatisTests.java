package com.swf.attence;

import com.swf.attence.entity.AdminMsg;
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
        /*System.out.println(iUserMsgService.selectUserMsgAndDeptMsg());*/

        String todayTable="icommand20190203";
        String r1 = UUID.randomUUID().toString().replace("-", "");
        ICommand inICommand = new ICommand();
        inICommand.setId(r1);
        inICommand.setIcommandUserid("111111111111");
        inICommand.setIcommandTime("2019-01-31 07:59:11");
        inICommand.setIcommandUsername("white.hou");
        inICommand.setIcommandCameraid("192.168.0.101");
        System.out.println(inICommand);
        iCommandMapper.insertIntoDatabase("icommand20190203",inICommand);
    }

}

