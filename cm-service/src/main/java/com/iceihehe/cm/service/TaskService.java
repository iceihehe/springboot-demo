package com.iceihehe.cm.service;

import com.iceihehe.cm.dao.entity.MTask;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaskService {

    List<MTask> getMTasks(int mobileAccountId, int pageNum, int pageSize);
    int getTotal(int mobileAccountId);
}
