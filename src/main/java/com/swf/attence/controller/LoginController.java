package com.swf.attence.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.swf.attence.entity.AdminMsg;
import com.swf.attence.service.IAdminMsgService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : white.hou
 * @description : 登录的controller
 * @date: 2018/12/30_20:32
 */
@Controller
public class LoginController {

    @PostMapping("/login")
    public String login(@RequestParam("adminid") String adminid, @RequestParam("password") String password, Model model){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(adminid, password);
        try {
            subject.login(usernamePasswordToken);
            Session session = subject.getSession();
            session.setAttribute("adminIdSession",adminid);
            return "/index";
        }catch (UnknownAccountException e) {
            /**
             * 登录失败:用户名不存在
             */
            model.addAttribute("msg", "用户名不存在，请校验后登录");
            return "/login";
        } catch (IncorrectCredentialsException e) {
            /**
             * 登录失败:密码错误
             */
            model.addAttribute("msg", "密码错误，请重新输入");
            return "/login";
        }
    }

    /**
     * 调转控制
     * @return
     */
    @RequestMapping("/tologin")
    public String tologin(){
         return "/login";
    }

    /**
     * 权限不足
     * @return
     */
    @RequestMapping("/noAuth")
    public  String noAuth(){
        return "/noAuth";
    }

}
