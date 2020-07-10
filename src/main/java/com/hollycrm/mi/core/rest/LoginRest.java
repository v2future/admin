package com.hollycrm.mi.core.rest;

import com.hollycrm.mi.core.entity.SysUser;
import com.hollycrm.mi.core.kit.MiKit;
import com.hollycrm.mi.core.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LoginRest {

    @Autowired
    UserService userService;

    @RequestMapping("/login")
    public ModelAndView login(String userCode, String password) {
        ModelAndView mv = new ModelAndView();
        if (StringUtils.isEmpty( userCode) || StringUtils.isEmpty( password)) {
            mv.setViewName("/login/login.btl");
            return mv;
        }
        //登录提交
        SysUser user = userService.get(userCode);
        if ( user == null) {
            mv.addObject("", "工号不存在！");
            mv.setViewName("/login/login.btl");
            return mv;
        }
        if (MiKit.lt( user.getPwd_valid_day(), MiKit.getCurrentDate())) {
            mv.addObject("", "工号密码已过期！");
            mv.setViewName("/login/login.btl");
            return mv;
        }
        String encodedPassword = MiKit.getMD5String(user.getPassword());
        if ( ! encodedPassword.equals( user.getPassword())) {
            mv.addObject("", "密码错误！");
            mv.setViewName("/login/login.btl");
            return mv;
        }
        //密码错误锁定

        UsernamePasswordToken token = new UsernamePasswordToken(user.getUser_name(), encodedPassword);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);

        return mv;
    }

    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/index.btl");
        return mv;
    }

    @RequestMapping("/logout")
    public String logout() {

        return null;
    }
}
