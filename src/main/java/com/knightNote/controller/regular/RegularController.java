package com.knightNote.controller.regular;

import com.knightNote.repository.regular.RegularRepository;
import com.knightNote.repository.regular.RegularSubRepository;
import com.knightNote.repository.regular.UserRegularRepository;
import com.knightNote.repository.regular.UserRegularSubRepository;
import com.knightNote.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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




}
