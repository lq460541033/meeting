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
}
