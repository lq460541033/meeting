package com.swf.attence.entity;

import com.baomidou.mybatisplus.enums.IdType;
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
    private Date checkInTime;
    @TableField("check_out_time")
    private Date checkOutTime;
    @TableField("check_in_from")
    private String checkInFrom;
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

    public Date getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(Date checkInTime) {
        this.checkInTime = checkInTime;
    }

    public Date getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(Date checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public String getCheckInFrom() {
        return checkInFrom;
    }

    public void setCheckInFrom(String checkInFrom) {
        this.checkInFrom = checkInFrom;
    }

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

    public static final String ID = "id";

    public static final String USERID = "userid";

    public static final String CHECK_IN_TIME = "check_in_time";

    public static final String CHECK_OUT_TIME = "check_out_time";

    public static final String CHECK_IN_FROM = "check_in_from";

    public static final String CHECK_STATE = "check_state";

    public static final String FAILID = "failid";

    @Override
    public String toString() {
        return "AttenceMsg{" +
        ", id=" + id +
        ", userid=" + userid +
        ", checkInTime=" + checkInTime +
        ", checkOutTime=" + checkOutTime +
        ", checkInFrom=" + checkInFrom +
        ", checkState=" + checkState +
        ", failid=" + failid +
        "}";
    }
}
