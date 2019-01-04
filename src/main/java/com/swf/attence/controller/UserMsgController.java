package com.swf.attence.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.swf.attence.service.IUserMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author : white.hou
 * @description :用户信息控制类
 * @date: 2019/1/3_10:30
 */
@Controller
public class UserMsgController {
    @Autowired
    private IUserMsgService iUserMsgService;

    /**
     * 分页
     * @param pageNum
     * @param pageSize
     * @param model
     * @return
     */
    @RequestMapping(value = "/userMsgs",method =GET)
   public String userItemPages(@RequestParam(value = "pageNum",required = false,defaultValue = "1") int pageNum, @RequestParam(value = "pageSize",required = false,defaultValue = "10") int pageSize, Model model){
       PageHelper.startPage(pageNum,pageSize);
        List list = iUserMsgService.selectUserMsgAndDeptMsg();
      /*  UserMsg o = (UserMsg)list.get(0);
        Convert convert = new Convert();
        Map<String, Object> map = convert.converObjToMap(o);
        System.out.println(map);*/
       PageInfo pageInfo = new PageInfo<>(list, 5);
       model.addAttribute("pageInfo",pageInfo);
       model.addAttribute("pageNum",pageNum);
       model.addAttribute("pageSize",pageSize);
       model.addAttribute("isFirstPage",pageInfo.isIsFirstPage());
       model.addAttribute("totalPages",pageInfo.getPages());
       model.addAttribute("isLastPage",pageInfo.isIsLastPage());
       return "userMsgControl/userMsg_list";
   }



}
