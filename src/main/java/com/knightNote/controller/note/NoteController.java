package com.knightNote.controller.note;

import com.knightNote.entity.user.User;
import com.knightNote.repository.note.NoteRepository;
import com.knightNote.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
            @RequestParam String content,
            @RequestHeader(required = false) String accessToken) {

        Map<String, Object> res = new HashMap<>();

        System.out.println(content);
//        User user =userRepository.findByAccessToken(accessToken);

        return res;
    }

}
