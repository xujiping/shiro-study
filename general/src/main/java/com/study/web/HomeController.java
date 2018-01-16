package com.study.web;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	private SecurityManager securityManager;

	@RequestMapping(value={"", "/index"})
	public String index() {
		return "/index";
	}

	@RequestMapping("/login")
	public String login() {
		return "/login";
	}

	@RequestMapping(value = "/ajaxLogin", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> doLogin(String username, String password) {
		LinkedHashMap<String, Object> map = new LinkedHashMap<>();
		try {
			//使用用户登录信息创建令牌
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
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
	
	@RequestMapping("/edit")
	@ResponseBody
	public String edit(){
		Subject subject = SecurityUtils.getSubject();
		if (subject.hasRole("admin")) {
			return "修改";
		}
		return "无权限";
	}

}
