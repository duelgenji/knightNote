package com.knightNote.controller.user;

import com.knightNote.entity.user.User;
import com.knightNote.entity.user.UserSettings;
import com.knightNote.repository.user.UserRepository;
import com.knightNote.repository.user.UserSettingsRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by knightNote on 15/10/9.
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserSettingsRepository userSettingsRepository;


    /**
     * 注册
     */
    @Transactional
    @RequestMapping("register")
    public Map<String, Object> register(
            @RequestParam String account,
            @RequestParam String password) {

        Map<String, Object> res = new HashMap<>();


        Map<String, Object> filters = new HashMap<>();

        if(account!=null){
            filters.put("account_equal", account);
        }

        if (userRepository.findAll(filters).size()>0) {
            res.put("success",0);
            res.put("message","账户名存在~请换一个试试吧!");
            return res;
        }else{
            User user = new User();
            user.setAccount(account);
            user.setPassword(DigestUtils.md5Hex(password));
            userRepository.save(user);

            UserSettings userSettings = new UserSettings();
            userSettings.setUser(user);
            userSettingsRepository.save(userSettings);
        }

        res.put("success",1);
        return res;
    }

    /**
     * 登陆
     */
    @RequestMapping("login")
    public Map<String, Object> login(
            @RequestParam String account,
            @RequestParam String password) {

        Map<String, Object> res = new HashMap<>();

        User user = userRepository.findByAccount(account);

        if(user==null){
            res.put("success",0);
            res.put("message","该账号不存在");
            return res;
        }

        user = userRepository.findByAccountAndPasswordAndRemoved(account, DigestUtils.md5Hex(password), false);

        if(user!=null){
            user.setAccessToken(UUID.randomUUID().toString());
            user.setAccessTime(new Date().getTime() + (long) 365 * 24 * 60 * 60 * 1000);
            userRepository.save(user);
            res.put("accessToken", user.getAccessToken());
        }else{
            res.put("success", 0);
            res.put("message", "账号密码错误");
            return res;
        }


        res.put("success",1);
        return res;
    }


    /**
     * 登出
     */
    @RequestMapping("logout")
    public Map<String ,Object> logout(
            @RequestHeader String accessToken){

        Map<String, Object> res = new HashMap<>();

        User user = userRepository.findByAccessToken(accessToken);

        if(user!=null){
            user.setAccessToken("");
            user.setAccessTime(0);
            userRepository.save(user);
        }

        res.put("success",1);
        return res;
    }

    /**
     * 登陆
     */
    @RequestMapping("iklogin")
    public Map<String ,Object> iklogin(
            HttpServletRequest request,
            @RequestParam(required = false) String account,
            @RequestParam(required = false) String password){

        Map<String, Object> res = new HashMap<>();

        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");

        res.put("success",1);
        res.put("account",  sdf.format(now)+"@qq.com");
        res.put("password", "12345678");

        res.put("remoteUser", request.getRemoteUser());
//        res.put("remoteAddr", request.getRemoteAddr());
        Enumeration<String> enumeration = request.getHeaderNames();
        while(enumeration.hasMoreElements()){
            res.put(enumeration.nextElement(), request.getHeader(enumeration.nextElement()));
        }
//        res.put("x-header", request.getHeader("x-forwarded-for"));
//        res.put("pc-header", request.getHeader("Proxy-Client-IP"));
//        res.put("wpc-header", request.getHeader("WL-Proxy-Client-IP"));


//        if (request.getHeader("x-forwarded-for") == null) {
//            System.out.println("remoteAddr : " + request.getRemoteAddr());
//        }else{
//            System.out.println("x-header : " + request.getHeader("x-forwarded-for"));
//        }



        return res;
    }

    @RequestMapping("test")
    public Object test(int code){

        Map<String, Object> res = new HashMap<>();
        res.put(code+"", HttpStatus.valueOf(code));
//
//        return res;
        return ResponseEntity.status(code).body(res);
    }

}

