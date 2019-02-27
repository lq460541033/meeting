package com.swf.attence.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author auto-genergator123
 * @since 2019-02-26
 */
@TableName("leave_msg")
public class LeaveMsg implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 用户工号
     */
    private String username;
    /**
     * 请假
     */
    private Integer fail;
    /**
     * 请假是否通过，1 通过 0未通过
     */
    private Integer access;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getFail() {
        return fail;
    }

    public void setFail(Integer fail) {
        this.fail = fail;
    }

    public Integer getAccess() {
        return access;
    }

    public void setAccess(Integer access) {
        this.access = access;
    }

    public static final String ID = "id";

    public static final String USERNAME = "username";

    public static final String FAIL = "fail";

    public static final String ACCESS = "access";

    @Override
    public String toString() {
        return "LeaveMsg{" +
        ", id=" + id +
        ", username=" + username +
        ", fail=" + fail +
        ", access=" + access +
        "}";
    }
}
