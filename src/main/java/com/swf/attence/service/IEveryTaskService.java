package com.swf.attence.service;

import com.swf.attence.entity.AttenceMsg;
import com.swf.attence.entity.ICommand;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
     * @param inCommandList
     * @param outCommandList
     * @param day
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    void everyDataAnalsis(String day,ArrayList<ICommand> inCommandList,ArrayList<ICommand> outCommandList);



    /**
     * 上一天"进'的数据  去重之后
     * @param day
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    ArrayList<ICommand> getInICommand(String day) throws ClassNotFoundException, SQLException;

    /**
     * 获取上一天'出'的数据 去重之后
     * @param day
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    ArrayList<ICommand> getOutICommand(String day) throws ClassNotFoundException, SQLException;


}
