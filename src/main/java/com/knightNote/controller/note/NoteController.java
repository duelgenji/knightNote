package com.knightNote.controller.note;

import com.knightNote.entity.note.Note;
import com.knightNote.entity.user.User;
import com.knightNote.repository.note.NoteRepository;
import com.knightNote.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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


        return res;
    }



    @RequestMapping(value = "retrieveMyNoteList")
    public Map<String, Object> retrieveMyNoteList(
            @PageableDefault(page = 0, size = 20,sort = "createDate", direction = Sort.Direction.DESC) Pageable pageable,
            @RequestHeader String accessToken) {

        Map<String, Object> res = new HashMap<>();

        User user = userRepository.findByAccessToken(accessToken);

        if(user==null){
            res.put("success",0);
            res.put("message","请先登录");
            res.put("error","000");
            return res;
        }

        Map<String, Object> filters = new HashMap<>();

        filters.put("removed_equal", 0);
        filters.put("user_equal", user);
        Page<Note> noteList = noteRepository.findAll(filters,pageable);

        res.put("list",noteList.getContent());
        res.put("success", 1);


        return res;
    }

    @RequestMapping(value="noteSquare")
    public Map<String, Object> noteSquare(
            @PageableDefault(page = 0, size = 20,sort = "createDate", direction = Sort.Direction.DESC) Pageable pageable){

        Map<String, Object> res = new HashMap<>();

        Map<String, Object> filters = new HashMap<>();

        filters.put("removed_equal", 0);
        Page<Note> noteList = noteRepository.findAll(filters,pageable);

        res.put("list",noteList.getContent());
        res.put("success", 1);
        return res;
    }

}
