package com.knightNote.controller.note;

import com.knightNote.entity.note.Note;
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
            @RequestHeader String accessToken) {

        Map<String, Object> res = new HashMap<>();

        System.out.println(" accessToken = [" + accessToken + "]");

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
        noteRepository.save(note);
        res.put("success", 1);

//        User user =userRepository.findByAccessToken(accessToken);

        return res;
    }

}
