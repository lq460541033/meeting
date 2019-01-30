package com.swf.attence.service;

import com.swf.attence.entity.ICommand;

import java.sql.SQLException;

/**
 * @author : white.hou
 * @description : 每日服务接口
 * @date: 2019/1/28_10:29
 */
public interface IEveryTaskService {
    /**
     * 每日建表 表名为 icommandyyyymmdd
     * @param day
     * @return
     */
    Boolean creatEveryDayTable(String day);

    /**
     * 将报警信息返还的结果插入到数据库中
     * @param iCommand
     * @return
     */
    Boolean insertEveryICommandIntoDateBase(ICommand iCommand) throws ClassNotFoundException, SQLException;

    /**
     * 获取昨日数据库中所有数据并分析
     * 分析与time_control中规定时间比对
     * @param day
     * @return
     */
    Boolean getEverydayDataAndAnalsis(String day);
}
