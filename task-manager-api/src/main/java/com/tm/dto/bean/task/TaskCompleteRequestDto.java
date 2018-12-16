package com.tm.dto.bean.task;

/**
 * タスク完了リクエストDTO。
 */
public class TaskCompleteRequestDto {

    /**
     * タスクID
     */
    private String taskId;

    public String getTaskId() {
        return taskId;
    }
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}
