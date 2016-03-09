package com.knightNote.controller.regular;

import com.knightNote.controller.GlobalControllerExceptionHandler;
import com.knightNote.controller.GlobalControllerExceptionHandler.*;
import com.knightNote.entity.user.User;
import com.knightNote.repository.regular.RegularRepository;
import com.knightNote.repository.regular.RegularSubRepository;
import com.knightNote.repository.regular.UserRegularRepository;
import com.knightNote.repository.regular.UserRegularSubRepository;
import com.knightNote.repository.user.UserRepository;
import com.knightNote.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.knightNote.entity.regular.ConvictCondition;
import com.knightNote.repository.regular.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Knight on 2015/11/24 23:52.
 */
@RestController
@RequestMapping("regular/")
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

    @Autowired
    ConvictConditionRepository convictConditionRepository;

    @Autowired
    UserService userService;

    /**
     * 囚徒健身 生成记录
     */
    @Transactional
    @RequestMapping("generateConvictCondition")
    public Object generateConvictCondition(
            @RequestParam int progression,
            @RequestParam int step,
            @RequestParam(required = false) String stepName,
            @RequestParam int level,
            @RequestParam int countType,
            @RequestParam int count,
            @RequestParam int countGroup,
            @RequestParam(required = false) String remark,
            @RequestHeader String accessToken){
        Map<String, Object> res = new HashMap<>();

        User user = userService.getUserByToken(accessToken);
        if(user==null){
            throw new GlobalControllerExceptionHandler.UserNotFoundException();
        }

        ConvictCondition cc = new ConvictCondition();
        cc.setUser(user);
        cc.setProgression(ConvictCondition.Progression.values()[progression]);
        cc.setStep(step);
        cc.setStepName(stepName);
        cc.setLevel(level);
        cc.setCountType(countType);
        cc.setCount(count);
        cc.setCountGroup(countGroup);
        cc.setRemark(remark);
        convictConditionRepository.save(cc);

        res.put("success",1);
        return res;

    }

    @RequestMapping("retrieveLast")
    public Object retrieveLast(@RequestHeader String accessToken){
        Map<String, Object> res = new HashMap<>();

        User user = userService.getUserByToken(accessToken);
        if(user==null){
            throw new GlobalControllerExceptionHandler.UserNotFoundException();
        }

        List<ConvictCondition> convictConditionList = convictConditionRepository.findEachProgressionLastOne(user.getId());

        res.put("success",1);
        res.put("convictConditionList",convictConditionList);
        return res;
    }


}
