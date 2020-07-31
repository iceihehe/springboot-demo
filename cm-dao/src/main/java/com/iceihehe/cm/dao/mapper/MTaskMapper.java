package com.iceihehe.cm.dao.mapper;

import com.iceihehe.cm.dao.entity.MTask;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MTaskMapper {
    List<MTask> getMTasks(@Param("mobileAccountId") int mobileAccountId, @Param("offsetNum") int offsetNum, @Param("limitNum") int limitNum);

    int getTotal(@Param("mobileAccountId") int mobileAccountId);
}
