package com.tm.dto.bean.task;

import com.tm.dto.common.Errors;

/**
 * タスク削除レスポンスDTO。
 */
public class TaskDeleteResponseDto {

    /**
     * タスクID
     */
    private String taskId;

    /**
     * エラーコード
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
