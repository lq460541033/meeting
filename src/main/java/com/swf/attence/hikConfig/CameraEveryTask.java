package com.swf.attence.hikConfig;

import com.swf.attence.service.IEveryTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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

    /**
     * 每天建立数据库表
     */
    @Scheduled(cron ="0 0 0 1/1 * ? ")
    public void creatTmpTable(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String day = dateFormat.format(new Date());
        iEveryTaskService.creatEveryDayTable(day);
    }

    /**
     * 每天凌晨对前一日数据分析，存入正式表
     * 动作以下几个：1 从每日表中拉取指定数据（进、出） 2 根据第一次进的时间（ip）、最后一次出的时间（ip）与time_control表的规定事项比较
     * 3 存入正式表
     */
    @Scheduled(cron = "0 0 0 * * ? ")
    public void dataAnalysis(){
    }
}
