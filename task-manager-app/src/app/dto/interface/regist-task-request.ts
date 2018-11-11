/**
 * タスク登録リクエストDTOです.
 */
export class RegistTaskRequest {
  
  /** 
   * タスクタイトル
   */
  private taskTitle: string;
  
  /**
   * タスクラベル
   */
  private taskLabel: string;
  
  /** 
   * 開始日
   */
  private startDate: Date;
  
  /** 
   * 期限日
   */
  private deadline: Date;
  
  /** 
   * 完了フラグ
   */
  private completedFlag: string;
  
  /** 
   * 利用者ID
   */
  private userId: string;
  
  /** 
   * タスクメモ 
   */
  private taskNote: string;

  public getTaskTitle () {
    return this.taskTitle;
  }
  public setTaskTitle (taskTitle: string) {
    this.taskTitle = taskTitle;
  }
  public getTaskLabel () {
    return this.taskLabel;
  }
  public setTaskLabel (taskLabel: string) {
    this.taskLabel = taskLabel;
  }
  public getStartDate () {
    return this.startDate;
  }
  public setStartDate (startDate: Date) {
    this.startDate = startDate;
  }
  public getDeadline () {
    return this.deadline;
  }
  public setDeadline (deadline: Date) {
    this.deadline = deadline;
  }
  public getCompletedFlag () {
    return this.completedFlag;
  }
  public setCompletedFlag (completedFlag: string) {
    this.completedFlag = completedFlag;
  }
  public getUserId () {
    return this.userId;
  }
  public setUserId (userId: string) {
    this.userId = userId;
  }
  public getTaskNote () {
    return this.taskNote;
  }
  public setTaskNote (taskNote: string) {
    this.taskNote = taskNote;
  }
}
