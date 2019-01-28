package com.swf.attence.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.swf.attence.entity.CameraMsg;
import com.swf.attence.hikConfig.ClientDemo;
import com.swf.attence.hikConfig.FDLibBox;
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
    @Autowired
    private ClientDemo clientDemo;
    @Autowired
    private FDLibBox fdLibBox;

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

    @Override
    public boolean cameraInitAndcameraRegisterAndsetupAlarmChan() {
        Boolean register=false;
        Integer integer = cameraMsgMapper.selectCount(new EntityWrapper<CameraMsg>().eq("1", 1));
        for (int i=1;i<=integer;i++){
            if ("初始化成功".equals(clientDemo.CameraInit())){
                CameraMsg cameraMsg = cameraMsgMapper.selectById(i);
                if(cameraMsg!=null){
                     if (clientDemo.register("admin", "admin123456", cameraMsg.getCameraid())){
                         if ("布防成功".equals(clientDemo.SetupAlarmChan())){
                             register=true;
                             return register;
                         }else {
                             System.out.println(clientDemo.SetupAlarmChan());
                         }
                     }else {
                         return register;
                     }
                }
            }else {
                return register;
            }
        }
        return register;
    }

    @Override
    public boolean uploadUserPicAndUserMessage() {
        boolean uploadState=false;
        Integer integer = cameraMsgMapper.selectCount(new EntityWrapper<CameraMsg>().eq("1", 1));
        for (int i=1;i<=integer;i++){

        }
        return false;
    }


}
