package com.knightNote.repository.regular;

import com.knightNote.entity.regular.ConvictCondition;
import com.wonders.xlab.framework.repository.MyRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by knight on 16/1/12.
 */
public interface ConvictConditionRepository extends MyRepository<ConvictCondition, Long> {

    @Query(value="select * from (SELECT * FROM convict_condition where user_id =?1 order by level desc ,created_date desc) cc group by cc.progression ;" ,nativeQuery = true)
    List<ConvictCondition> findEachProgressionLastOne(Long userId);
}
