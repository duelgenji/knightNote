package com.knightNote.service.user;

import com.knightNote.entity.user.User;
import com.knightNote.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by knight on 16/2/23.
 */
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Object validateUser(String accessToken){
        User user = userRepository.findByAccessToken(accessToken);

        System.out.println("va user");
        if(user==null){
            Map<String, Object> res = new HashMap<>();
            res.put("success",0);
            res.put("message","请先登录");
            res.put("error","000");
            return res;
        }else{
            return null;
        }
    }

}
