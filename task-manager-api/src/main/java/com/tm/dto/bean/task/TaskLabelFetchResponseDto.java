package com.tm.dto.bean.task;

import java.util.List;

import com.tm.dto.TaskLabel;
import com.tm.dto.common.Errors;

/**
 * タスクラベル情報取得結果DTOクラス。
 */
public class TaskLabelFetchResponseDto {

    /**
     *
     */
    private List<TaskLabel> labels;

    /**
     * Error結果
     */
    private Errors errors;

    public List<TaskLabel> getLabels() {
        return labels;
    }
    public void setLabels(List<TaskLabel> labels) {
        this.labels = labels;
    }
    public Errors getErrors() {
        return errors;
    }
    public void setErrors(Errors errors) {
        this.errors = errors;
    }

}
