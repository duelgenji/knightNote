package com.knightNote.service.user;

import com.knightNote.controller.GlobalControllerExceptionHandler;
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


    public User getUserByToken(String token){
        User user = userRepository.findByAccessToken(token);
        if(user==null){
            throw new GlobalControllerExceptionHandler.UserNotFoundException();
        }
        return user;
    }

}
