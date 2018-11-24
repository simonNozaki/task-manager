package com.tm.dto.bean.task;

import com.tm.dto.common.Errors;

public class TaskCompleteResponseDto {

    /**
     * タスクID
     */
    private String taskId;

    /**
     * Error結果
     */
    private Errors errors;

    public String getTaskId() {
        return taskId;
    }
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
    public Errors getErrors() {
        return errors;
    }
    public void setErrors(Errors errors) {
        this.errors = errors;
    }

}
