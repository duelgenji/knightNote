package com.knightNote.controller.user;

import com.knightNote.entity.user.User;
import com.knightNote.entity.user.UserSettings;
import com.knightNote.repository.user.UserRepository;
import com.knightNote.repository.user.UserSettingsRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
            res.put("success",0);
            res.put("message","账号密码错误");
            return res;
        }

        res.put("success",1);
        return res;
    }


}

