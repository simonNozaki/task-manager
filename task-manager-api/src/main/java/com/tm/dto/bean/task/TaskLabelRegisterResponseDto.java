package com.tm.dto.bean.task;

import com.tm.dto.common.Errors;

/**
 * タスクラベル登録レスポンスDTOクラス。
 */
public class TaskLabelRegisterResponseDto {

    /**
     * ラベルID
     */
    private String labelId;

    /**
     * タスクラベル（名）
     */
    private String taskLabel;

    /**
     * エラー結果
     */
    private Errors errors;

    public String getLabelId() {
        return labelId;
    }
    public void setLabelId(String labelId) {
        this.labelId = labelId;
    }
    public String getTaskLabel() {
        return taskLabel;
    }
    public void setTaskLabel(String taskLabel) {
        this.taskLabel = taskLabel;
    }
    public Errors getErrors() {
        return errors;
    }
    public void setErrors(Errors errors) {
        this.errors = errors;
    }

}
