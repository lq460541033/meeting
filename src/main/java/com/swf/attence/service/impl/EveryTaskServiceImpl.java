package com.swf.attence.service.impl;

import com.swf.attence.service.IEveryTaskService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.*;

/**
 * 每日建表服务类 建立的表名规则：icommandYYYYMMDD
 */
@Service
public class EveryTaskServiceImpl implements IEveryTaskService {
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
    public String prefix="icommand";
    @Override
    public Boolean creatEveryDayTable(String day) {
        Boolean  todayTableExist=false;
        try {
            System.out.println(driver+url+username+password);
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            String todayTable=prefix + day;
            ResultSet tables = connection.getMetaData().getTables(null, null, todayTable, null);
            if (tables.next()){
                return todayTableExist;
            }else {
                statement.executeUpdate("DROP TABLE IF EXISTS "+todayTable+";");
                statement.executeUpdate("    CREATE TABLE `attence`.`"+todayTable+"`  (\n" +
                        "  `id` int(0) NOT NULL,\n" +
                        "  `icommand_time` datetime(0) NOT NULL COMMENT '报警时间',\n" +
                        "  `icommand_cameraid` varchar(255) NOT NULL COMMENT '报警来源：摄像头ip',\n" +
                        "  `icommand_userid` varchar(255) NOT NULL COMMENT '用户工号 ',\n" +
                        "  `icommand_username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户姓名',\n" +
                        "  PRIMARY KEY (`id`)\n" +
                        ") ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci;");
                statement.close();
                connection.close();
                todayTableExist=true;
                return todayTableExist;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todayTableExist;
    }


}
