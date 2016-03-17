package com.knightNote.controller.regular;

import com.knightNote.controller.GlobalControllerExceptionHandler.UserNotFoundException;
import com.knightNote.entity.regular.Arrangement;
import com.knightNote.entity.regular.ConvictCondition;
import com.knightNote.entity.user.User;
import com.knightNote.repository.regular.*;
import com.knightNote.repository.user.UserRepository;
import com.knightNote.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Knight on 2015/11/24 23:52.
 */
@RestController
@RequestMapping("arrangement/")
public class ArrangementController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RegularRepository regularRepository;

    @Autowired
    ArrangementRepository arrangementRepository;

    @Autowired
    UserService userService;


    /**
     * 计划列表
     */
    @RequestMapping("retrieveArrangements")
    public Object retrieveLast(@RequestHeader String accessToken){
        Map<String, Object> res = new HashMap<>();

        User user = userService.getUserByToken(accessToken);
        if(user==null){
            throw new UserNotFoundException();
        }

        List<Arrangement> arrangements = arrangementRepository.findByUser(user);

        res.put("success",1);
        res.put("arrangements",arrangements);
        return res;
    }

    /**
     * 新建 短期计划
     */
    @RequestMapping("generateArrangement")
    public Object generateArrangement(
            @RequestParam String title,
            @RequestParam(required = false) String remark,
            @RequestHeader String accessToken){
        Map<String, Object> res = new HashMap<>();

        User user = userService.getUserByToken(accessToken);
        if(user==null){
            throw new UserNotFoundException();
        }

        Arrangement arrangement = new Arrangement();
        arrangement.setUser(user);
        arrangement.setTitle(title);
        arrangement.setRemark(remark);

        arrangementRepository.save(arrangement);

        res.put("success",1);
        return res;

    }

    /**
     * 修改 短期计划
     * @param action 0 取消 1 完成
     */
    @RequestMapping("modifyArrangement")
    public Object modifyArrangement(
            @RequestParam long arrangementId,
            @RequestParam int action,
            @RequestHeader String accessToken){
        Map<String, Object> res = new HashMap<>();

        User user = userService.getUserByToken(accessToken);
        if(user==null){
            throw new UserNotFoundException();
        }

        Arrangement arrangement = arrangementRepository.findOne(arrangementId);

        switch (action){
            case 0:
                arrangement.setCancel(true);
                break;
            default:
                arrangement.setComplete(true);
                break;
        }

        arrangementRepository.save(arrangement);

        res.put("success",1);
        return res;

    }

}
