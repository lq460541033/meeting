package com.swf.attence.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.swf.attence.entity.CameraMsg;
import com.swf.attence.entity.DeptMsg;
import com.swf.attence.entity.UserMsg;
import com.swf.attence.service.ICameraMsgService;
import com.swf.attence.service.IDeptMsgService;
import com.swf.attence.service.IUserMsgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author : white.hou
 * @description : ajax请求专用controller
 * @date: 2019/2/11_15:02
 */
@RestController
public class AjaxController {
    @Autowired
    private IUserMsgService iUserMsgService;
    @Autowired
    private ICameraMsgService iCameraMsgService;
    @Autowired
    private IDeptMsgService iDeptMsgService;

    private static final Logger logger = LoggerFactory.getLogger(AjaxController.class);

    /**
     * ajax检查 添加用户考勤信息
     * @param userid
     * @param cameraidIn
     * @param cameraidOut
     * @return
     */
    @RequestMapping(value = "/attenceMsg/check",method = POST)
    @ResponseBody
    public Map<String,String> checkAttenceMsg(@RequestParam("userid") String userid, @RequestParam("cameraidIn") String cameraidIn, @RequestParam("cameraidOut") String cameraidOut){
        UserMsg userMsg = iUserMsgService.selectOne(new EntityWrapper<UserMsg>().eq("userid", userid));
        CameraMsg msg = iCameraMsgService.selectOne(new EntityWrapper<CameraMsg>().eq("cameraid", cameraidIn));
        CameraMsg msg1 = iCameraMsgService.selectOne(new EntityWrapper<CameraMsg>().eq("cameraid", cameraidOut));
        DeptMsg deptMsg = iDeptMsgService.selectOne(new EntityWrapper<DeptMsg>().eq("deptid", userMsg.getDeptid()));
        HashMap<String, String> stringStringHashMap = new HashMap<>(16);
        stringStringHashMap.put("useridMsg",userMsg.getUserid());
        stringStringHashMap.put("userNameMsg",userMsg.getUsername());
        stringStringHashMap.put("deptMsg",deptMsg.getDeptname());
        stringStringHashMap.put("cameraIn",msg.getCameraPosition());
        stringStringHashMap.put("cameraOut",msg1.getCameraPosition());
        return stringStringHashMap;
    }

    /**
     * 导出考勤数据
     * @param day
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/generateReports",method = POST)
    @ResponseBody
    public String generateReports(@RequestParam("day") String day) throws IOException {
        String message;
         if (iUserMsgService.generateExcel(day)){
             message="已成功导出  "+day+"考勤数据";
             logger.info(message);
             return message;
         }else {
             message="未知错误，请重试";
             logger.info(message);
             return message;
         }
    }
}
