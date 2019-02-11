package com.swf.attence;

import com.swf.attence.entity.ICommand;
import com.swf.attence.entity.UserMsg;
import com.swf.attence.service.IEveryTaskService;
import com.swf.attence.service.IUserMsgService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AttenceApplicationTests {
    @Autowired
   private IUserMsgService userMsgService;
    @Autowired
    private IEveryTaskService iEveryTaskService;
    @Test
    public void contextLoads() throws SQLException, ClassNotFoundException {
        String r1 = UUID.randomUUID().toString().replace("-", "");
        String replace = UUID.randomUUID().toString().replace("-", "");
        ArrayList<ICommand> inICommands = new ArrayList<>(16);
        ICommand inICommand = new ICommand();
        inICommand.setId(r1);
        inICommand.setIcommandUserid("00000000");
        inICommand.setIcommandTime("2019-02-01 07:59:11");
        inICommand.setIcommandUsername("white.hou");
        inICommand.setIcommandCameraid("192.168.0.101");
        inICommands.add(inICommand);
        ArrayList<ICommand> outICommands = new ArrayList<>(16);
        ICommand inICommand1 = new ICommand();
        inICommand1.setId(replace);
        inICommand1.setIcommandUserid("00000000");
        inICommand1.setIcommandTime("2019-02-01 22:01:01");
        inICommand1.setIcommandUsername("white.hou");
        inICommand1.setIcommandCameraid("192.168.0.102");
        outICommands.add(inICommand1);
        iEveryTaskService.everyDataAnalsis("20190201",inICommands,outICommands);




    }

}

