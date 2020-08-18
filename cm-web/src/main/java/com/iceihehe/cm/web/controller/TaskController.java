package com.iceihehe.cm.web.controller;

import com.iceihehe.cm.dao.entity.MTask;
import com.iceihehe.cm.service.TaskService;
import com.iceihehe.cm.utils.processor.TimeProcessor;
import com.iceihehe.cm.web.dto.GwResp;
import com.iceihehe.cm.web.pojo.TaskListReqData;
import com.iceihehe.cm.web.pojo.TaskListRespData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @RequestMapping(path = "/api/monitor-task")
    public GwResp<TaskListRespData> getTaskList(@Valid TaskListReqData taskListReqData) {
        List<TaskListRespData> taskListRespDataList = new ArrayList<>();
        List<MTask> mTasks = taskService.getMTaskList(taskListReqData.getMobileAccountId(), taskListReqData.getPageNum(), taskListReqData.getPageSize());
        for (MTask mTask : mTasks) {
            TaskListRespData taskListRespData = new TaskListRespData();
            taskListRespData.setAppType(mTask.getAppType());
            taskListRespData.setStatus(mTask.getStatus());
            taskListRespData.setCreateTime(TimeProcessor.process(mTask.getCreateTime()));
            taskListRespData.setStartTime(TimeProcessor.process(mTask.getStartTime()));
            taskListRespData.setUpdateTime(TimeProcessor.process(mTask.getUpdateTime()));
            taskListRespData.setAppName(mTask.getSupportedApp().getName());
            taskListRespDataList.add(taskListRespData);
        }
        GwResp<TaskListRespData> resp = new GwResp<>();
        resp.setData(taskListRespDataList);
        resp.setTotal(taskService.getTotal(taskListReqData.getMobileAccountId()));
        return resp;
    }
}
