package com.dictionary.web.repository.batis;


import com.dictionary.web.domain.SlotStat;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SlotStatRepository {
    //https://stackoverflow.com/questions/29082749/spring-data-jpa-map-the-native-query-result-to-non-entity-pojo
    @Select(value = "select slotId,overdueCount,waitingCount,studiedCount,totalCount from expr_statistic()")
    @Results({
            @Result(property = "slotId", column = "slotId"),
            @Result(property = "overdueCount", column = "overdueCount"),
            @Result(property = "waitingCount", column = "waitingCount"),
            @Result(property = "studiedCount", column = "studiedCount"),
            @Result(property = "totalCount", column = "totalCount")
    })
    List<SlotStat> getSlotStat();
}
