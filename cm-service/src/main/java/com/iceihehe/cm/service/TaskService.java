package com.iceihehe.cm.service;

import com.iceihehe.cm.dao.entity.MTask;

import java.util.List;

public interface TaskService {

    List<MTask> getMTasks(int mobileAccountId);
    int getTotal(int mobileAccountId);
}
