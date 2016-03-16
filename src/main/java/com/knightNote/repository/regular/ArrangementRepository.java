package com.knightNote.repository.regular;

import com.knightNote.entity.regular.Arrangement;
import com.knightNote.entity.user.User;
import com.wonders.xlab.framework.repository.MyRepository;

import java.util.List;

public interface ArrangementRepository extends MyRepository<Arrangement, Long> {

    List<Arrangement> findByUser(User user);

}
