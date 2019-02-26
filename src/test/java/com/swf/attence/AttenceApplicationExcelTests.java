package com.swf.attence;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.swf.attence.entity.AttenceMsg;
import com.swf.attence.entity.ICommand;
import com.swf.attence.mapper.AttenceMsgMapper;
import com.swf.attence.mapper.ICommandMapper;
import com.swf.attence.service.IAdminMsgService;
import com.swf.attence.service.IAttenceMsgService;
import com.swf.attence.service.IEveryTaskService;
import com.swf.attence.service.IUserMsgService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AttenceApplicationExcelTests {
    @Autowired
   private IAttenceMsgService iAttenceMsgService;

    private AttenceMsgMapper attenceMsgMapper;
    @Autowired
    private IUserMsgService iUserMsgService;
    @Test
    public void contextLoads() throws IOException {
      /*iAttenceMsgService.generateExcel("20190201");*/
        /*List<AttenceMsg> attenceMsgs = iAttenceMsgService.selectList(new EntityWrapper<AttenceMsg>().like("check_in_time",   "2019%"));
        System.out.println(attenceMsgs);*/
      /*  iUserMsgService.generateExcel("20190201");*/
        List<AttenceMsg> attenceMsgs = iAttenceMsgService.selectList(new EntityWrapper<AttenceMsg>().like("check_in_time", "2019-02-01" + "%"));
        System.out.println(attenceMsgs);
    }

}

