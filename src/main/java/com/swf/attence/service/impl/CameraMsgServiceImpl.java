package com.swf.attence.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.swf.attence.entity.CameraMsg;
import com.swf.attence.mapper.CameraMsgMapper;
import com.swf.attence.service.ICameraMsgService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author auto-genergator
 * @since 2018-12-30
 */
@Service
public class CameraMsgServiceImpl extends ServiceImpl<CameraMsgMapper, CameraMsg> implements ICameraMsgService {
    @Autowired
    private CameraMsgMapper cameraMsgMapper;

    /**
     * 检查添加的设备id是否存在，存在返回false，不存在返回true
     * @param cameraMsg
     * @return
     */
    @Override
    public boolean cameraidExist(CameraMsg cameraMsg){
        CameraMsg cameraMsg1 = cameraMsgMapper.selectOne(cameraMsg);
        System.out.println(cameraMsg1);
        if (cameraMsg1==null){
            return  true;
        }else {
            return false;
        }
     /*  return cameraMsg1==null;*/
    }
}
