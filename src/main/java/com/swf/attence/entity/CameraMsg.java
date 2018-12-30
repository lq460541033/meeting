package com.swf.attence.entity;

import com.baomidou.mybatisplus.enums.IdType;
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
@TableName("camera_msg")
public class CameraMsg implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 摄像机id
     */
    private String cameraid;
    /**
     * 摄像机位置 比如A楼A室
     */
    @TableField("camera_location")
    private String cameraLocation;
    /**
     * 摄像机具体位置
     */
    @TableField("camera_position")
    private String cameraPosition;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCameraid() {
        return cameraid;
    }

    public void setCameraid(String cameraid) {
        this.cameraid = cameraid;
    }

    public String getCameraLocation() {
        return cameraLocation;
    }

    public void setCameraLocation(String cameraLocation) {
        this.cameraLocation = cameraLocation;
    }

    public String getCameraPosition() {
        return cameraPosition;
    }

    public void setCameraPosition(String cameraPosition) {
        this.cameraPosition = cameraPosition;
    }

    public static final String ID = "id";

    public static final String CAMERAID = "cameraid";

    public static final String CAMERA_LOCATION = "camera_location";

    public static final String CAMERA_POSITION = "camera_position";

    @Override
    public String toString() {
        return "CameraMsg{" +
        ", id=" + id +
        ", cameraid=" + cameraid +
        ", cameraLocation=" + cameraLocation +
        ", cameraPosition=" + cameraPosition +
        "}";
    }
}
