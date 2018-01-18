package com.study.web;

import com.study.model.User;
import com.study.service.UserService;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 注册
 *
 * @author xujiping 2018-01-17 17:18
 */
@Controller
@RequestMapping("/signUp")
public class SignUpController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String signUp() {
        return "signup";
    }

    @PostMapping("signUp.do")
    public String doSignUp(User user){
        // TODO 校验参数
        String salt1 = user.getUsername();
        String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
        salt2 = "708b3cf46c2f0d20341832e5b9c8d4d2";
        int hashIterations = 2;
        SimpleHash hash = new SimpleHash("md5", user.getPassword(), salt1 + salt2, hashIterations);
        user.setPassword(hash.toHex());  //加密后的密码
        user.setSalt(salt2);
        user.setCtime(System.currentTimeMillis());
        userService.add(user);
        return "login";
    }
}
