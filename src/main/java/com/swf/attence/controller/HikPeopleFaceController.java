package com.swf.attence.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.swf.attence.entity.CameraMsg;
import com.swf.attence.hikConfig.ClientDemo;
import com.swf.attence.hikConfig.FDLibBox;
import com.swf.attence.service.ICameraMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author : white.hou
 * @description : 海康摄像头的controller
 * @date: 2019/1/26_19:59
 */
@RestController
public class HikPeopleFaceController {
    @Autowired
    private ICameraMsgService iCameraMsgService;



}
