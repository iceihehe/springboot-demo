package com.iceihehe.cm.service.impl;

import com.iceihehe.cm.dao.entity.MTask;
import com.iceihehe.cm.dao.mapper.MTaskMapper;
import com.iceihehe.cm.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private MTaskMapper mTaskMapper;


    public List<MTask> getMTasks(int mobileAccountId) {
        return mTaskMapper.getMTasks(mobileAccountId);
    }
    public int getTotal(int mobileAccountId) {
        return mTaskMapper.getTotal(mobileAccountId);
    }
}
