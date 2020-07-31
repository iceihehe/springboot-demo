package com.iceihehe.cm.service;

import com.iceihehe.cm.dao.entity.MTask;

import java.util.List;

public interface TaskService {

    List<MTask> getMTaskList(int mobileAccountId, int pageNum, int pageSize);
    int getTotal(int mobileAccountId);
}
