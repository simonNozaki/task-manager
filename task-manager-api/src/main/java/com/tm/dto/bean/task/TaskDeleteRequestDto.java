package com.tm.dto.bean.task;

/**
 * タスク削除リクエストDTO。
 */
public class TaskDeleteRequestDto {

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
