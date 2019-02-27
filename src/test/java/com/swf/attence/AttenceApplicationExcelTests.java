package com.swf.attence;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.swf.attence.entity.AttenceMsg;
import com.swf.attence.entity.ICommand;
import com.swf.attence.entity.LeaveMsg;
import com.swf.attence.mapper.AttenceMsgMapper;
import com.swf.attence.mapper.ICommandMapper;
import com.swf.attence.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AttenceApplicationExcelTests {
    @Autowired
   private IAttenceMsgService iAttenceMsgService;
    @Autowired
    private AttenceMsgMapper attenceMsgMapper;
    @Autowired
    private IUserMsgService iUserMsgService;
    @Autowired
    private ILeaveMsgService iLeaveMsgService;

    @Test
    public void contextLoads() throws IOException {
      /*iAttenceMsgService.generateExcel("20190201");*/
        /*List<AttenceMsg> attenceMsgs = iAttenceMsgService.selectList(new EntityWrapper<AttenceMsg>().like("check_in_time",   "2019%"));
        System.out.println(attenceMsgs);*/
      /*  iUserMsgService.generateExcel("20190201");*/
     /*   List<AttenceMsg> attenceMsgs = iAttenceMsgService.selectList(new EntityWrapper<AttenceMsg>().like("check_in_time", "2019-02-01" + "%"));
        System.out.println(attenceMsgs);*/
       /* Collection<AttenceMsg> attenceMsgs = attenceMsgMapper.selectByTimeAndState("2019%", 1);
        System.out.println(attenceMsgs);*/
       /*iUserMsgService.generateEveryDayMsg("2019-02",1);*/
        ArrayList<AttenceMsg> attenceMsgs = attenceMsgMapper.selectByTimeAndState( "2019-02%", 2);
        System.out.println(attenceMsgs);

    }

}

