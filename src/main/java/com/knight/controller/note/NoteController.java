package com.knight.controller.note;

import com.knight.entity.user.User;
import com.knight.repository.note.NoteRepository;
import com.knight.repository.user.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by knight on 15/10/16.
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
            HttpServletRequest request) {

        Map<String, Object> res = new HashMap<>();

        System.out.println(request.getParameter("username"));
        System.out.println(request.getParameter("password"));
        System.out.println(request.getAttribute("username"));
        System.out.println(request.getAttribute("password"));
        System.out.println(request.getHeader("username"));
        System.out.println(request.getHeader("password"));

        return res;
    }

}
