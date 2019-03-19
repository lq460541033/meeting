package com.swf.attence.service;

import com.swf.attence.entity.AttenceMsg;
import com.swf.attence.entity.ICommand;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    Boolean insertIntoDatabase(String todayTable,ICommand iCommand);
    /**
     * 参数是已经处理完成的两个list 这个方法是把符合考勤要求的写入正式库中
     * @param day
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    void everyDataAnalsis(String day) throws SQLException, ClassNotFoundException;


    /**
     * 从数据库中获取指定数据 封装到Map中
     * @param userid
     * @param sql
     * @return
     */
    Map<String,ICommand> getDataFromDatabase(String userid,String sql) throws ClassNotFoundException, SQLException;

    /**
     * 同一个Userid的最大最小Map比较分析
     * @param maxMap
     * @param minMap
     * @param day
     * @param userid
     */
    Boolean analysisMap(String day,String userid,Map<String,ICommand> maxMap,Map<String,ICommand> minMap);
}
