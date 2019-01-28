package com.swf.attence.service;

import com.swf.attence.entity.CameraMsg;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author auto-genergator
 * @since 2018-12-30
 */
public interface ICameraMsgService extends IService<CameraMsg> {
    /**
     * 判断添加的设备是否存在，存在返回false。不存在返回true
     * @param cameraMsg
     * @return
     */
    boolean cameraidExist(CameraMsg cameraMsg);

    /**
     * 设备的初始化、注册,成功注册返回true
     * @return
     */
    boolean cameraInitAndcameraRegisterAndsetupAlarmChan();

    /**
     * 下发人脸信息
     * @return
     */
    boolean uploadUserPicAndUserMessage();

}
