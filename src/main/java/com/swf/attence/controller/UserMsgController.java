package com.swf.attence.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.swf.attence.entity.DeptMsg;
import com.swf.attence.entity.UserMsg;
import com.swf.attence.service.IDeptMsgService;
import com.swf.attence.service.IUserMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    @Autowired
    private IDeptMsgService iDeptMsgService;

    public static  final  String PATH="F:\\Attence相关\\userpic\\";

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

    /**
     * 跳转添加用户页面
     * @return
     */
   @GetMapping("/userMsg")
   public String toInsertUserMsg(Model model){
       List<DeptMsg> deptMsgs = iDeptMsgService.selectList(new EntityWrapper<DeptMsg>().eq("1", 1));
       model.addAttribute("deptMsgs",deptMsgs);
       System.out.println(deptMsgs);
       return "userMsgControl/userMsg_add";
   }

    /**
     * 编辑用户 跳转到编辑（添加）页面
     * @param id
     * @param model
     * @return
     */
   @GetMapping("/userMsg/{id}")
   public String toUpdateUserMsg(@PathVariable("id") Integer id, Model model){
       UserMsg userMsg = iUserMsgService.selectById(id);
       model.addAttribute("userMsg",userMsg);
       List<DeptMsg> deptMsgs = iDeptMsgService.selectList(new EntityWrapper<DeptMsg>().eq("1", 1));
       model.addAttribute("deptMsgs",deptMsgs);
       System.out.println(deptMsgs);
       return "userMsgControl/userMsg_add";
   }

    /**
     * 添加单个用户
     * @param userMsg
     * @return
     */
   @PostMapping("/userMsg")
   public String insertUserMsg(UserMsg userMsg,Model model){
       iUserMsgService.insert(userMsg);
       return "redirect:/userMsgs";
   }

    /**
     * 添加单个用户
     * @param userMsg
     * @return
     */
    @PutMapping("/userMsg")
    public String updateUserMsg(UserMsg userMsg){
        iUserMsgService.updateById(userMsg);
        return "redirect:/userMsgs";
    }

    /**
     * 删除指定员工
     * @param id
     * @return
     */
   @DeleteMapping("/userMsg/{id}")
   public  String deleteUserMsg(@PathVariable("id") Integer id){
       iUserMsgService.deleteById(id);
       return "redirect:/userMsgs";
   }

   @PostMapping("/userMsg/uploadImg")
   public String uploadImsg( @RequestParam MultipartFile multipartFile,Model model){
       multipartFile.getOriginalFilename();
       if (!multipartFile.isEmpty()){
           try {
               Files.copy(multipartFile.getInputStream(), Paths.get(PATH, multipartFile.getOriginalFilename()));
               model.addAttribute("ImgUploadMsg","success");
           } catch (IOException e) {
               e.printStackTrace();
               model.addAttribute("ImgUploadMsg","error");
           }
       }else {
           model.addAttribute("ImgUploadMsg","error");
       }
       return "userMsgControl/userMsg_add";
   }

}
