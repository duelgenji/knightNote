package com.knightNote.controller.system;

import com.knightNote.entity.system.GuestBook;
import com.knightNote.repository.system.GuestBookRepository;
import com.knightNote.service.wx.WxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by knight on 15/10/23.
 */
@RestController
@RequestMapping("guestBook")
public class GuestBookController {

    @Autowired
    GuestBookRepository guestBookRepository;

    @Autowired
    WxService wxService;

    @RequestMapping("generateGuestBook")
    public Map<String ,Object> generateGuestBook(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam String message
    ){
        Map<String, Object> res = new HashMap<>();


        GuestBook guestBook= new GuestBook();
        guestBook.setEmail(email);
        guestBook.setName(name);
        guestBook.setMessage(message);
        guestBookRepository.save(guestBook);

        res.put("success", 1);
        return res;
    }


}
