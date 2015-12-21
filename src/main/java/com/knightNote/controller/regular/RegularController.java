package com.knightNote.controller.regular;

import com.knightNote.entity.note.Note;
import com.knightNote.entity.user.User;
import com.knightNote.repository.regular.RegularRepository;
import com.knightNote.repository.regular.RegularSubRepository;
import com.knightNote.repository.regular.UserRegularRepository;
import com.knightNote.repository.regular.UserRegularSubRepository;
import com.knightNote.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Knight on 2015/11/24 23:52.
 */
@RestController
@RequestMapping("regular")
public class RegularController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RegularRepository regularRepository;

    @Autowired
    RegularSubRepository regularSubRepository;

    @Autowired
    UserRegularRepository userRegularRepository;

    @Autowired
    UserRegularSubRepository userRegularSubRepository;

    @RequestMapping(value = "generateNote")
    public Map<String, Object> generateNote(
            @RequestParam String content,
            @RequestHeader String accessToken) {

        Map<String, Object> res = new HashMap<>();

        User user = userRepository.findByAccessToken(accessToken);

        if(user==null){
            res.put("success",0);
            res.put("message","请先登录");
            res.put("error","000");
            return res;
        }


        Note note = new Note();
        note.setContent(content);
        note.setUser(user);
        res.put("success", 1);


        return res;
    }


}
