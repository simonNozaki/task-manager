package com.tm.dto.bean.task;

/**
 * タスクラベル登録リクエストDTOクラス。
 */
public class TaskLabelRegisterRequestDto {

    /**
     * タスクラベル（名）
     */
    private String taskLabel;

    /**
     * ラベル利用フラグ
     */
    private String usedFlag;

    /**
     * 利用者ID
     */
    private String userId;

    public String getTaskLabel() {
        return taskLabel;
    }
    public void setTaskLabel(String taskLabel) {
        this.taskLabel = taskLabel;
    }
    public String getUsedFlag() {
        return usedFlag;
    }
    public void setUsedFlag(String usedFlag) {
        this.usedFlag = usedFlag;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

}
