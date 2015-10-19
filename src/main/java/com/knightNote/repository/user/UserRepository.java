package com.knightNote.repository.user;

import com.knightNote.entity.user.User;
import com.wonders.xlab.framework.repository.MyRepository;

/**
 * Created by knightNote on 15/10/9.
 */
public interface UserRepository extends MyRepository<User, Long> {

    User findByAccount(String account);

    User findByAccountAndPasswordAndRemoved(String account,String password,boolean isRemoved);

    User findByAccessToken(String accessToken);

}
