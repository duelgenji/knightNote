package com.knightNote.controller.note;

import com.knightNote.entity.user.User;
import com.knightNote.repository.note.NoteRepository;
import com.knightNote.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by knightNote on 15/10/16.
 */
@RestController
@RequestMapping("note")
public class NoteController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    NoteRepository noteRepository;


    @RequestMapping(value = "generateNote")
    public Map<String, Object> generateNote(
            @RequestHeader(required = false) String accessToken,
            HttpServletRequest request) {

        Map<String, Object> res = new HashMap<>();

//        User user =userRepository.findByAccessToken(accessToken);

        System.out.println(UUID.randomUUID().toString());
        return res;
    }

}
