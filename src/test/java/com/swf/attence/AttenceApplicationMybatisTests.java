package com.swf.attence;

import com.swf.attence.entity.AdminMsg;
import com.swf.attence.service.IAdminMsgService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AttenceApplicationMybatisTests {
    @Autowired
    private IAdminMsgService iAdminMsgService;

    @Test
    public void contextLoads() {
       /* AdminMsg adminMsg = iAdminMsgService.selectById(1);
        System.out.println(adminMsg);*/
       AdminMsg adminMsg=new AdminMsg();

    }

}

