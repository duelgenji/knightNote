package com.knightNote.controller.note;

import com.knightNote.entity.note.NoteTag;
import com.knightNote.repository.note.NoteTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by knight on 15/10/23.
 */
@RestController
@RequestMapping("noteTag")
public class NoteTagController {

    @Autowired
    NoteTagRepository noteTagRepository;

    @RequestMapping("generateTag")
    public Map<String ,Object> generateTag(
            @RequestParam String content
    ){
        Map<String, Object> res = new HashMap<>();

        NoteTag noteTag = noteTagRepository.findByContent(content);

        if(noteTag!=null){
            res.put("success",0);
            res.put("message","已经存在标签");
            return res;
        }

        noteTag = new NoteTag();
        noteTag.setContent(content);
        noteTagRepository.save(noteTag);

        res.put("success", 1);
        return res;
    }

    @RequestMapping("retrieveList")
    public Map<String ,Object> retrieveList(
            @PageableDefault(page = 0, size = 20,sort = "createDate", direction = Sort.Direction.DESC) Pageable pageable
    ){
        Map<String, Object> res = new HashMap<>();

        Page<NoteTag> noteTags = noteTagRepository.findAll(pageable);

        res.put("list", noteTags.getContent());

        res.put("success", 1);
        return res;
    }

}
