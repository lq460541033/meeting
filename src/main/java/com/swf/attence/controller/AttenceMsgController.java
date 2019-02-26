package com.swf.attence.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.swf.attence.entity.AttenceMsg;
import com.swf.attence.entity.CameraMsg;
import com.swf.attence.service.IAttenceMsgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author : white.hou
 * @description :正式考勤信息展示
 * @date: 2019/2/7_10:00
 */
@Controller
public class AttenceMsgController {
    @Autowired
    private IAttenceMsgService iAttenceMsgService;

    private static final Logger logger = LoggerFactory.getLogger(AttenceMsgController.class);

    /**
     * 分页展示正式考勤信息
     * @param pageNum
     * @param pageSize
     * @param model
     */
    @RequestMapping(value = "/attenceMsgs",method = GET)
    public String attenceMsgPages(@RequestParam(value = "pageNum",required = false,defaultValue = "1") int pageNum, @RequestParam(value = "pageSize",required = false,defaultValue = "10") int pageSize, Model model){
        PageHelper.startPage(pageNum, pageSize);
        List<AttenceMsg> attenceMsgs = iAttenceMsgService.selectList(new EntityWrapper<AttenceMsg>().eq("1", 1));
        System.out.println(attenceMsgs);
        PageInfo<AttenceMsg> attenceMsgPageInfo = new PageInfo<>(attenceMsgs, 5);
        model.addAttribute("attenceMsgPageInfo",attenceMsgPageInfo);
        model.addAttribute("pageNum",pageNum);
        model.addAttribute("pageSize",pageSize);
        model.addAttribute("isFirstPage",attenceMsgPageInfo.isIsFirstPage());
        model.addAttribute("totalPages",attenceMsgPageInfo.getPages());
        model.addAttribute("isLastPage",attenceMsgPageInfo.isIsLastPage());
        return "attenceMsgControl/attenceMsg_list";
    }

    /**
     * 跳转到添加页面
     * @return
     */
    @GetMapping("/attenceMsg")
    public String toInsert(){
        return "attenceMsgControl/attenceMsg_add";
    }

    /**
     * 添加用户考勤信息
     * @param attenceMsg
     * @param model
     * @return
     */
    @PostMapping("/attenceMsg")
    public String insertIntoAttenceMsg(AttenceMsg attenceMsg,Model model){
        if(!iAttenceMsgService.attenceMsgExist(attenceMsg)){
            iAttenceMsgService.insert(attenceMsg);
            model.addAttribute("attenceMsg","添加成功");
            logger.info(attenceMsg+"添加成功");
        }else {
            model.addAttribute("attenceMsg","错误，该用户已存在");
            logger.info(attenceMsg+"已存在");
        }
        return "redirect:/attenceMsgs";
    }

    /**
     * 跳转到生成报表界面
     * @return
     */
    @GetMapping("/generate")
    public String toGeneratReports(){
        return "attenceMsgControl/attenceMsg_generate";
    }
}
