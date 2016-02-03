package com.knightNote.controller.regular;

import com.knightNote.entity.user.User;
import com.knightNote.repository.regular.RegularRepository;
import com.knightNote.repository.regular.RegularSubRepository;
import com.knightNote.repository.regular.UserRegularRepository;
import com.knightNote.repository.regular.UserRegularSubRepository;
import com.knightNote.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.knightNote.entity.regular.ConvictCondition;
import com.knightNote.repository.regular.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Knight on 2015/11/24 23:52.
 */
@RestController
@RequestMapping("regular")
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

    /**
     * 囚徒健身  记录
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
            @RequestParam(required = false) String remark,
            @RequestHeader String accessToken){
        Map<String, Object> res = new HashMap<>();

        User user = userRepository.findByAccessToken(accessToken);

        if(user==null){
            res.put("success",0);
            res.put("message","请先登录");
            res.put("error","000");
            return res;
        }


        ConvictCondition cc = new ConvictCondition();
        cc.setUser(user);
        cc.setProgression(ConvictCondition.Progression.values()[progression]);
        cc.setStep(step);
        cc.setStepName(stepName);
        cc.setLevel(level);
        cc.setCount(count);
        cc.setCountType(countType);
        cc.setRemark(remark);
        convictConditionRepository.save(cc);

        return res;


    }


}
