package com.knight.controller.user;

import com.knight.entity.user.User;
import com.knight.repository.user.UserRepository;
import com.knight.repository.user.UserSettingsRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by knight on 15/10/9.
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserSettingsRepository userSettingsRepository;


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
        }else{
            User user = new User();
            user.setAccount(account);
            user.setPassword(DigestUtils.md5Hex(password));
            userRepository.save(user);
        }

        res.put("success",1);
        return res;
    }

}

