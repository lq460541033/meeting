package com.swf.attence.entity;

import com.baomidou.mybatisplus.enums.IdType;

import java.sql.Timestamp;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author auto-genergator
 * @since 2018-12-30
 */
@TableName("attence_msg")
public class AttenceMsg implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户工号
     */
    private String userid;
    @TableField("check_in_time")
    private String checkInTime;
    @TableField("cameraid_in")
    private String cameraidIn;
    @TableField("check_out_time")
    private String checkOutTime;

    @TableField("cameraid_out")
    private String cameraidOut;
   /* @TableField("check_in_from")
    private String checkInFrom;*/
    /**
     * 状态 考勤状态  0 考勤成功 1 考勤失败
     */
    @TableField("check_state")
    private Integer checkState;
    /**
     * 如果 考勤失败 事由
     */
    private Integer failid;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(String checkInTime) {
        this.checkInTime = checkInTime;
    }

    public String getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(String checkOutTime) {
        this.checkOutTime = checkOutTime;
    }
/*
    public String getCheckInFrom() {
        return checkInFrom;
    }

    public void setCheckInFrom(String checkInFrom) {
        this.checkInFrom = checkInFrom;
    }*/

    public Integer getCheckState() {
        return checkState;
    }

    public void setCheckState(Integer checkState) {
        this.checkState = checkState;
    }

    public Integer getFailid() {
        return failid;
    }

    public void setFailid(Integer failid) {
        this.failid = failid;
    }
    public String getCameraidIn() {
        return cameraidIn;
    }

    public void setCameraidIn(String cameraidIn) {
        this.cameraidIn = cameraidIn;
    }

    public String getCameraidOut() {
        return cameraidOut;
    }

    public void setCameraidOut(String cameraidOut) {
        this.cameraidOut = cameraidOut;
    }

    public static final String ID = "id";

    public static final String USERID = "userid";

    public static final String CHECK_IN_TIME = "check_in_time";

    public static final String CHECK_OUT_TIME = "check_out_time";

  /*  public static final String CHECK_IN_FROM = "check_in_from";*/

    public static final String CHECK_STATE = "check_state";

    public static final String FAILID = "failid";

    public static final String CAMERAID_IN="cameraid_in";
    public static final String CAMERAID_OUT="cameraid_out";


    @Override
    public String toString() {
        return "AttenceMsg{" +
                "id=" + id +
                ", userid='" + userid + '\'' +
                ", checkInTime='" + checkInTime + '\'' +
                ", cameraidIn='" + cameraidIn + '\'' +
                ", checkOutTime='" + checkOutTime + '\'' +
                ", cameraidOut='" + cameraidOut + '\'' +
                ", checkState=" + checkState +
                ", failid=" + failid +
                '}';
    }
}
