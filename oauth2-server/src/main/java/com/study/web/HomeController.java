package com.study.web;

import com.study.service.Oauth2ClientService;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xujiping
 * @version 2017年4月26日 下午5:28:41 类说明
 */
@Controller
public class HomeController {

    @Autowired
    private Oauth2ClientService oauth2ClientService;

    @RequestMapping(value = {"", "/index"})
    public String index(Model model) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        model.addAttribute("sessionId", session.getId());
        //查询oauth2 client列表
        model.addAttribute("oauth2Clients", oauth2ClientService.findAll());
        return "/index";
    }

    @RequestMapping("/edit")
    @ResponseBody
    public String edit() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole("admin")) {
            return "修改";
        }
        return "无权限";
    }

}
