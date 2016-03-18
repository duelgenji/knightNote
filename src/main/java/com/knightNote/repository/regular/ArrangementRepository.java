package com.knightNote.repository.regular;

import com.knightNote.entity.regular.Arrangement;
import com.knightNote.entity.user.User;
import com.wonders.xlab.framework.repository.MyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArrangementRepository extends MyRepository<Arrangement, Long> {

    Page<Arrangement> findByUser(User user ,Pageable pageable);

}
