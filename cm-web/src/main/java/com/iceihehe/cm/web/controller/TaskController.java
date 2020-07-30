package com.iceihehe.cm.web.controller;

import com.iceihehe.cm.dao.entity.MTask;
import com.iceihehe.cm.service.TaskService;
import com.iceihehe.cm.web.dto.GwResp;
import com.iceihehe.cm.web.pojo.TaskListReqData;
import com.iceihehe.cm.web.pojo.TaskListRespData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @RequestMapping(path = "/api/monitor-task")
    public GwResp<TaskListRespData> getTaskList(TaskListReqData taskListReqData) {
        List<TaskListRespData> taskListRespDataList = new ArrayList<>();
        List<MTask> mTasks = taskService.getMTasks(taskListReqData.getMobileAccountId());
        System.out.println(mTasks);
        for (MTask mTask : mTasks) {
            TaskListRespData taskListRespData = new TaskListRespData();
            taskListRespData.setAppType(mTask.getAppType());
            taskListRespData.setStatus(mTask.getStatus());
//            taskListRespData.setStartTime(mTask.getStartTime().getTime());
            taskListRespData.setAppName(mTask.getSupportedApp().getName());
            taskListRespDataList.add(taskListRespData);
        }
        GwResp<TaskListRespData> resp = new GwResp<>();
        resp.setData(taskListRespDataList);
        resp.setTotal(taskService.getTotal(taskListReqData.getMobileAccountId()));
        return resp;
    }
}
