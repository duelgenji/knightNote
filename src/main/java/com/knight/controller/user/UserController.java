package com.knight.controller.user;

import com.knight.repository.user.UserRepository;
import com.knight.repository.user.UserSettingsRepository;
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
            @RequestParam(required = true) String account,
            @RequestParam(required = true) String password) {

        Map<String, Object> res = new HashMap<>();

        res.put("success",1);
        return res;
    }

}

