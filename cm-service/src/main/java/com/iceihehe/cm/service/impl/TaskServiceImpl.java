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


    public List<MTask> getMTaskList(int mobileAccountId, int pageNum, int pageSize) {
        return mTaskMapper.getMTaskList(mobileAccountId, (pageNum - 1) * pageSize, pageSize);

    }
    public int getTotal(int mobileAccountId) {
        return mTaskMapper.getTotal(mobileAccountId);
    }
}
