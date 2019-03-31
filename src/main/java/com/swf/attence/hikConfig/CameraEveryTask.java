package com.swf.attence.hikConfig;

import com.swf.attence.service.IEveryTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 每日任务，包括临时报警数据存放、数据分析、数据解析后写入正式库等任务
 */
@Component
@EnableScheduling
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
        System.out.println("今日: "+day+"数据表创建完成");
    }
    /**
     * 每天凌晨1时对前一日数据分析，存入正式表
     * 动作以下几个：1 从每日表中拉取指定数据（进、出） 2 根据第一次进的时间（ip）、最后一次出的时间（ip）与time_control表的规定事项比较
     * 3 存入正式表
     */
    @Scheduled(cron = "0 0 1 1/1 * ?")
    public void dataAnalysis() throws SQLException, ClassNotFoundException {
        /**
         * 获取上一天日期
         */
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        instance.add(Calendar.DATE,-1);
        Date time = instance.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String format = simpleDateFormat.format(time);
        iEveryTaskService.everyDataAnalsis(format);
        System.out.println(format+"  数据分析完成，请登录查看");
    }
    @Scheduled(cron = "0 0 0/1 1/1 * ? ")
    public void scheduledTest(){
        System.out.println("这是个调度测试程序");
    }

}
