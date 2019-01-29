package com.swf.attence.hikConfig;

import com.swf.attence.service.IEveryTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 每日任务，包括临时报警数据存放、数据分析、数据解析后写入正式库等任务
 */
@Component
public class CameraEveryTask {
    @Autowired
    private IEveryTaskService iEveryTaskService;
    @Scheduled(cron ="0 0 0 1/1 * ? ")
    public void creatTmpTable(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmdd");
        String day = dateFormat.format(new Date());
        iEveryTaskService.creatEveryDayTable(day);
    }
}
