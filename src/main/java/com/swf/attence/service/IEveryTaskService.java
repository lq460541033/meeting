package com.swf.attence.service;
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
}
