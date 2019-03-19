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
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AttenceApplicationTests {
    @Autowired
    private IUserMsgService userMsgService;
    @Autowired
    private IEveryTaskService iEveryTaskService;

    @Test
    public void contextLoads() throws SQLException, ClassNotFoundException {
        iEveryTaskService.everyDataAnalsis("20190207");
    }
}

