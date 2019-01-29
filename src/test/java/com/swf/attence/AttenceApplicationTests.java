package com.swf.attence;

import com.swf.attence.entity.UserMsg;
import com.swf.attence.service.IEveryTaskService;
import com.swf.attence.service.IUserMsgService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AttenceApplicationTests {
    @Autowired
   private IUserMsgService userMsgService;
    @Autowired
    private IEveryTaskService iEveryTaskService;
    @Test
    public void contextLoads() {
        /*UserMsg userMsg = userMsgService.selectUserMsgAndDeptMsgById(1);
        System.out.println(userMsg);*/
       /* List list = userMsgService.selectUserMsgAndDeptMsgByUserid("1100");
        System.out.println(list);*/
        SimpleDateFormat yyyymmdd = new SimpleDateFormat("yyyymmdd");
        String format = yyyymmdd.format(new Date());
        Boolean aBoolean = iEveryTaskService.creatEveryDayTable(format);

        System.out.println(aBoolean);

    }

}

