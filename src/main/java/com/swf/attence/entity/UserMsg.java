package com.swf.attence.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author auto-genergator
 * @since 2018-12-30
 */
@TableName("user_msg")
public class UserMsg implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户工号
     */
    private String userid;
    /**
     * 用户姓名
     */
    private String username;

    private  Integer gender;
    private String deptid;
    private String userpic;


    @TableField(exist = false)
    private DeptMsg dept;


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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDeptid() {
        return deptid;
    }

    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }

    public String getUserpic() {
        return userpic;
    }

    public void setUserpic(String userpic) {
        this.userpic = userpic;
    }
    public Integer getGender() {
        return gender;
    }
    public DeptMsg getDept() {
        return dept;
    }

    public void setDept(DeptMsg dept) {
        this.dept = dept;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public static final String ID = "id";

    public static final String USERID = "userid";

    public static final String USERNAME = "username";
    public static final String GENDER = "gender";

    public static final String DEPTID = "deptid";

    public static final String USERPIC = "userpic";
    public static final String DEPT = "dept";

    @Override
    public String toString() {
        return "UserMsg{" +
        ", id=" + id +
        ", userid=" + userid +
        ", username=" + username +
         ", gender=" + gender +
        ", deptid=" + deptid +
        ", userpic=" + userpic +
         ", dept=" + dept +
        "}";
    }
}
