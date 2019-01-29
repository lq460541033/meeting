package com.swf.attence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
/**
 * @author : white.hou
 * @description : 报警表的实体类
 * @date: 2019/1/29_11:25
 */
@Entity
public class ICommand {
    @Id
    private Integer id;
    @Column
    private String icommandTime;
    @Column
    private String icommandCameraid;
    @Column
    private String icommandUserid;
    @Column
    private String icommandUsername;

    public ICommand() {

    }

    @Override
    public String toString() {
        return "ICommand{" +
                "id=" + id +
                ", icommandTime=" + icommandTime +
                ", icommandCameraid='" + icommandCameraid + '\'' +
                ", icommandUserid='" + icommandUserid + '\'' +
                ", icommandUsername='" + icommandUsername + '\'' +
                '}';
    }

    public ICommand(Integer id, String icommandTime, String icommandCameraid, String icommandUserid, String icommandUsername) {
        this.id = id;
        this.icommandTime = icommandTime;
        this.icommandCameraid = icommandCameraid;
        this.icommandUserid = icommandUserid;
        this.icommandUsername = icommandUsername;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIcommandTime() {
        return icommandTime;
    }

    public void setIcommandTime(String icommandTime) {
        this.icommandTime = icommandTime;
    }

    public String getIcommandCameraid() {
        return icommandCameraid;
    }

    public void setIcommandCameraid(String icommandCameraid) {
        this.icommandCameraid = icommandCameraid;
    }

    public String getIcommandUserid() {
        return icommandUserid;
    }

    public void setIcommandUserid(String icommandUserid) {
        this.icommandUserid = icommandUserid;
    }

    public String getIcommandUsername() {
        return icommandUsername;
    }

    public void setIcommandUsername(String icommandUsername) {
        this.icommandUsername = icommandUsername;
    }
}
