package com.study.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 登录
 *
 * @author xujiping 2018-01-18 14:07
 */
@Controller
public class LoginController {

    @Autowired
    private SecurityManager securityManager;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/ajaxLogin", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> doLogin(String username, String password, boolean rememberMe) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        try {
            //使用用户登录信息创建令牌
            UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
            //执行登陆动作
            SecurityUtils.setSecurityManager(securityManager);
            SecurityUtils.getSubject().login(token);
            map.put("status", 200);
            map.put("message", "登录成功");
        } catch (Exception e) {
            map.put("status", 500);
            map.put("message", e.getMessage());
        }
        return map;
    }
}
