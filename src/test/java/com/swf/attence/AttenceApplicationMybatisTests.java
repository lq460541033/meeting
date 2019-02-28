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
        ArrayList<ICommand> iCommands = new ArrayList<>();
        ArrayList<ICommand> iCommands1 = new ArrayList<>();
        String r1 = UUID.randomUUID().toString().replace("-", "");
        String r2 = UUID.randomUUID().toString().replace("-", "");
        ICommand inICommand = new ICommand();
        inICommand.setId(r1);
        inICommand.setIcommandUserid("11111102");
        inICommand.setIcommandTime("2019-01-31 07:59:11");
        inICommand.setIcommandUsername("胡伯伯");
        inICommand.setIcommandCameraid("10.21.244.166");
        iCommands.add(inICommand);
        ICommand inICommand1 = new ICommand();
        inICommand1.setId(r2);
        inICommand1.setIcommandUserid("11111102");
        inICommand1.setIcommandTime("2019-01-31 15:00:00");
        inICommand1.setIcommandUsername("胡伯伯");
        inICommand1.setIcommandCameraid("10.21.244.167");
        iCommands1.add(inICommand1);
        iEveryTaskService.everyDataAnalsis("20190131",iCommands,iCommands1);
    }

}

