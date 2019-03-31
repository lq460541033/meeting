package com.swf.attence.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author auto-genergator123
 * @since 2019-03-31
 */
@TableName("meeting_count")
public class MeetingCount implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 用户id
     */
    private String userid;
    /**
     * 用户进入时间
     */
    @TableField("acess_time")
    private byte[] acessTime;
    /**
     * 用户进入的设备ID
     */
    @TableField("acess_camera_id")
    private byte[] acessCameraId;
    /**
     * 用户会议签到状态 0 正常签到 1 迟到 2 没有签到
     */
    @TableField("acess_state")
    private Integer acessState;
    /**
     * 如果会议失败 事由 1 已请假 0 未请假
     */
    @TableField("fail_id")
    private Integer failId;
    /**
     * 短信下发状态 0 成功 1 失败
     */
    @TableField("message_state")
    private Integer messageState;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public byte[] getAcessTime() {
        return acessTime;
    }

    public void setAcessTime(byte[] acessTime) {
        this.acessTime = acessTime;
    }

    public byte[] getAcessCameraId() {
        return acessCameraId;
    }

    public void setAcessCameraId(byte[] acessCameraId) {
        this.acessCameraId = acessCameraId;
    }

    public Integer getAcessState() {
        return acessState;
    }

    public void setAcessState(Integer acessState) {
        this.acessState = acessState;
    }

    public Integer getFailId() {
        return failId;
    }

    public void setFailId(Integer failId) {
        this.failId = failId;
    }

    public Integer getMessageState() {
        return messageState;
    }

    public void setMessageState(Integer messageState) {
        this.messageState = messageState;
    }

    public static final String ID = "id";

    public static final String USERID = "userid";

    public static final String ACESS_TIME = "acess_time";

    public static final String ACESS_CAMERA_ID = "acess_camera_id";

    public static final String ACESS_STATE = "acess_state";

    public static final String FAIL_ID = "fail_id";

    public static final String MESSAGE_STATE = "message_state";

    @Override
    public String toString() {
        return "MeetingCount{" +
        ", id=" + id +
        ", userid=" + userid +
        ", acessTime=" + acessTime +
        ", acessCameraId=" + acessCameraId +
        ", acessState=" + acessState +
        ", failId=" + failId +
        ", messageState=" + messageState +
        "}";
    }
}
