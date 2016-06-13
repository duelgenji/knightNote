package com.knightNote.controller.stream;

import com.knightNote.entity.stream.Streamer;
import com.knightNote.repository.stream.StreamerLiveRepository;
import com.knightNote.repository.stream.StreamerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by knightNote on 15/10/9.
 */
@RestController
@RequestMapping("stream")
public class StreamController {

    @Autowired
    StreamerRepository streamerRepository;

    @Autowired
    StreamerLiveRepository streamerLiveRepository;


    /**
     * 注册
     */
    @Transactional
    @RequestMapping("retrieveStreamerList")
    public Map<String, Object> retrieveStreamerList() {

        Map<String, Object> res = new HashMap<>();

        List<Streamer> streamerList = streamerRepository.findAll();

        res.put("success",1);
        res.put("list",streamerList);
        return res;
    }

}

