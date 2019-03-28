package com.swf.attence.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;

import com.swf.attence.controller.AttenceMsgController;
import com.swf.attence.entity.*;
import com.swf.attence.mapper.ICommandMapper;
import com.swf.attence.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * 每日建表服务类 建立的表名规则：icommandYYYYMMDD
 */
@Service
public class EveryTaskServiceImpl implements IEveryTaskService {
    @Autowired
    private ITimeControlService iTimeControlService;
    @Autowired
    private ICameraMsgService iCameraMsgService;
    @Autowired
    private IAttenceMsgService iAttenceMsgService;
    @Autowired
    private ICommandMapper iCommandMapper;
    @Autowired
    private IUserMsgService iUserMsgService;

    private static final Logger logger = LoggerFactory.getLogger(EveryTaskServiceImpl.class);

    @Value(value = "${spring.datasource.driver-class-name}")
    private String driver;
    @Value(value = "${spring.datasource.url}")
    private String url;
    @Value(value = "${spring.datasource.username}")
    private String username;
    @Value(value = "${spring.datasource.password}")
    private String password;
    /**
     * 表明前缀prefix
     */
    public String prefix = "icommand";

    @Override
    public Boolean creatEveryDayTable(String day) {
        Boolean todayTableExist = false;
        try {
            System.out.println(driver + url + username + password);
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            String todayTable = prefix + day;
            ResultSet tables = connection.getMetaData().getTables(null, null, todayTable, null);
            if (tables.next()) {
                return todayTableExist;
            } else {
                statement.executeUpdate("DROP TABLE IF EXISTS " + todayTable + ";");
                statement.executeUpdate("    CREATE TABLE `attence`.`" + todayTable + "`  (\n" +
                        "  `id` varchar(255) NOT NULL ,\n" +
                        "  `icommand_time` varchar(255) NOT NULL COMMENT '报警时间',\n" +
                        "  `icommand_cameraid` varchar(255) NOT NULL COMMENT '报警来源：摄像头ip',\n" +
                        "  `icommand_userid` varchar(255) NOT NULL COMMENT '用户工号 ',\n" +
                        "  `icommand_username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户姓名',\n" +
                        "  PRIMARY KEY (`id`)\n" +
                        ") ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci;");
                statement.close();
                connection.close();
                todayTableExist = true;
                return todayTableExist;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todayTableExist;
    }

    @Override
    public Boolean insertEveryICommandIntoDateBase(ICommand iCommand) throws ClassNotFoundException, SQLException {
        Boolean insertEveryICommandIntoDateBase = false;
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();
        /**
         * 获取当前表名
         */
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String day = dateFormat.format(new Date());
        String todayTable = prefix + day;
        ResultSet tables = connection.getMetaData().getTables(null, null, todayTable, null);
        if (!tables.next()) {
            statement.executeUpdate("DROP TABLE IF EXISTS " + todayTable + ";");
            statement.executeUpdate("    CREATE TABLE `attence`.`" + todayTable + "`  (\n" +
                    "  `id` varchar(255) NOT NULL ,\n" +
                    "  `icommand_time` varchar(255) NOT NULL COMMENT '报警时间',\n" +
                    "  `icommand_cameraid` varchar(255) NOT NULL COMMENT '报警来源：摄像头ip',\n" +
                    "  `icommand_userid` varchar(255) NOT NULL COMMENT '用户工号 ',\n" +
                    "  `icommand_username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户姓名',\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ") ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci;");
        }
        String sql = "INSERT INTO " + todayTable + "(" +
                "`id`, `icommand_time`, `icommand_cameraid`, `icommand_userid`, `icommand_username`) VALUES (?,?,?,?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, iCommand.getId());
        preparedStatement.setString(2, iCommand.getIcommandTime());
        preparedStatement.setString(3, iCommand.getIcommandCameraid());
        preparedStatement.setString(4, iCommand.getIcommandUserid());
        preparedStatement.setString(5, iCommand.getIcommandUsername());
        preparedStatement.executeUpdate();
        System.out.println("插入成功");
        statement.close();
        connection.close();
        insertEveryICommandIntoDateBase = true;
        return insertEveryICommandIntoDateBase;
    }
    @Override
    public Boolean insertIntoDatabase(String todayTable, ICommand iCommand) {
        iCommandMapper.insertIntoDatabase(todayTable,iCommand);
        return true;
    }


    @Override
    public Boolean analysisMap(String day,String userid,Map<String, ICommand> maxMap, Map<String, ICommand> minMap) {
        if (maxMap==null||minMap==null){
            return false;
        }
        ICommand maxICommand = maxMap.get(userid);
        ICommand minICommand = minMap.get(userid);
        if (maxICommand==null||minICommand==null){
            return false;
        }
        String maxIcommandTime = maxICommand.getIcommandTime();
        String minIcommandTime = minICommand.getIcommandTime();
        String maxSplit = maxIcommandTime.substring(11, 19);
        String minSplit = minIcommandTime.substring(11, 19);
        String userShoudBeInTime = iTimeControlService.userShoudBeInTime(day);
        String userShoudBeOutTime = iTimeControlService.userShoudBeOutTime(day);
        int minCompareTo = minSplit.compareTo(userShoudBeInTime);
        int maxCompareTo = maxSplit.compareTo(userShoudBeOutTime);
        if (minCompareTo<=0&&maxCompareTo>=0){
            AttenceMsg completeAttenceMsg = new AttenceMsg();
            completeAttenceMsg.setUserid(maxICommand.getIcommandUserid());
            completeAttenceMsg.setCameraidIn(minICommand.getIcommandCameraid());
            completeAttenceMsg.setCameraidOut(maxICommand.getIcommandCameraid());
            completeAttenceMsg.setCheckInTime(minICommand.getIcommandTime());
            completeAttenceMsg.setCheckOutTime(maxICommand.getIcommandTime());
            completeAttenceMsg.setCheckState(1);
            iAttenceMsgService.insert(completeAttenceMsg);
            return true;
        }else if (minCompareTo<=0 && maxCompareTo< 0){
            AttenceMsg completeAttenceMsg = new AttenceMsg();
            completeAttenceMsg.setUserid(maxICommand.getIcommandUserid());
            completeAttenceMsg.setCameraidIn(minICommand.getIcommandCameraid());
            completeAttenceMsg.setCameraidOut(maxICommand.getIcommandCameraid());
            completeAttenceMsg.setCheckInTime(minICommand.getIcommandTime());
            completeAttenceMsg.setCheckOutTime(maxICommand.getIcommandTime());
            completeAttenceMsg.setCheckState(3);
            iAttenceMsgService.insert(completeAttenceMsg);
            return true;
        }else if (minCompareTo >0 && maxCompareTo>= 0){
            AttenceMsg completeAttenceMsg = new AttenceMsg();
            completeAttenceMsg.setUserid(maxICommand.getIcommandUserid());
            completeAttenceMsg.setCameraidIn(minICommand.getIcommandCameraid());
            completeAttenceMsg.setCameraidOut(maxICommand.getIcommandCameraid());
            completeAttenceMsg.setCheckInTime(minICommand.getIcommandTime());
            completeAttenceMsg.setCheckOutTime(maxICommand.getIcommandTime());
            completeAttenceMsg.setCheckState(2);
            iAttenceMsgService.insert(completeAttenceMsg);
            return true;
        }else if (minCompareTo >0 && maxCompareTo< 0){
            AttenceMsg completeAttenceMsg = new AttenceMsg();
            completeAttenceMsg.setUserid(maxICommand.getIcommandUserid());
            completeAttenceMsg.setCameraidIn(minICommand.getIcommandCameraid());
            completeAttenceMsg.setCameraidOut(maxICommand.getIcommandCameraid());
            completeAttenceMsg.setCheckInTime(minICommand.getIcommandTime());
            completeAttenceMsg.setCheckOutTime(maxICommand.getIcommandTime());
            completeAttenceMsg.setCheckState(4);
            iAttenceMsgService.insert(completeAttenceMsg);
            return true;
        }
        return false;
    }

    @Override
    public void everyDataAnalsis(String day) throws SQLException, ClassNotFoundException {
        /**
         * 今日要处理的数据表名
         */
        String todayTable=prefix+day;
        /**
         * 遍历员工表 拼接sql
         */
        List<UserMsg> userMsgs = iUserMsgService.selectList(new EntityWrapper<UserMsg>().eq("1", 1));
       for (int  i=0;i<userMsgs.size();i++) {
           UserMsg userMsg = userMsgs.get(i);
           String userid = userMsg.getUserid();
           String maxSql = "SELECT  * FROM " + todayTable + "  WHERE icommand_userid=" + userid + " ORDER BY icommand_time desc LIMIT 1";
           Map<String, ICommand> maxMap = getDataFromDatabase(userid, maxSql);
           if (maxMap != null) {
               logger.info("发送给数据库的查询最大值得语句是：  " + maxSql + ";  获取的指定员工的最大Map是：  " + maxMap);
               String minSql = "SELECT  * FROM " + todayTable + "  WHERE icommand_userid=" + userid + " ORDER BY icommand_time asc LIMIT 1";
               Map<String, ICommand> minMap = getDataFromDatabase(userid, minSql);
               if (minMap != null) {
                   logger.info("发送给数据库的查询最小值得语句是：  " + maxSql + ";  获取的指定员工的最小Map是：  " + minMap);
                   System.out.println(maxMap);
                   System.out.println(minMap);
                   if (analysisMap(day, userid, maxMap, minMap)) {
                       logger.debug("今日： " + day + "工号： " + userid + "考勤数据处理完成");
                       System.out.println("今日： " + day + "工号： " + userid + "考勤数据处理完成");
                   }
               }
           }
       }
    }

    @Override
    public Map<String, ICommand> getDataFromDatabase(String userid,String sql) throws ClassNotFoundException, SQLException {
        /**
         * JDBC操作
         */
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();
        PreparedStatement inPreparedStatement = connection.prepareStatement(sql);
        ResultSet inResultSet = inPreparedStatement.executeQuery();
        HashMap<String, ICommand> map = new HashMap<>(16);
        while (inResultSet.next()) {
            ICommand inICommand = new ICommand();
            inICommand.setIcommandTime(inResultSet.getString(2));
            inICommand.setIcommandCameraid(inResultSet.getString(3));
            inICommand.setIcommandUsername(inResultSet.getString(5));
            inICommand.setIcommandUserid(inResultSet.getString(4));
            map.put(userid,inICommand);

        }
        statement.close();
        connection.close();
        return map;
    }
    
    /**
     * 去重
     *
     * @param iCommand
     * @return
     */
    public static ArrayList<ICommand> removeDuplicateICommand(List<ICommand> iCommand) {
        TreeSet<ICommand> iCommands = new TreeSet<>(new Comparator<ICommand>() {
            @Override
            public int compare(ICommand o1, ICommand o2) {
                return o1.getIcommandUserid().compareTo(o2.getIcommandUserid());
            }
        });
        iCommands.addAll(iCommand);
        return new ArrayList<ICommand>(iCommands);
    }

}
