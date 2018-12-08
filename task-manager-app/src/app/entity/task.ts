/**
 * タスクモデルの汎用エンティティクラスです.
 */
export class Task {

  /**
   * タスクID
   */
  public taskId: string;
  
  /** 
   * タスクタイトル
   */
  public taskTitle: string;
  
  /** 
   * タスクラベル
   */
  public taskLabel: string;
  
  /** 
   * 開始日
   */
  public startDate: Date;
  
  /** 
   * 期限日
   */
  public deadline: Date;
  
  /** 
   * 完了フラグ 
   */
  public completedFlag: string;
  
  /** 
   * 利用者ID 
   */
  public userId: string;
  
  /** 
   * タスクメモ 
   */
  public taskNote: string;

  public getTaskId(): string {
    return this.taskId;
  }
  public setTaskId(taskId: string) {
    this.taskId = taskId;
  }
  public getTaskTitle(): string {
    return this.taskTitle;
  }
  public setTaskTitle(taskTitle: string) {
    this.taskTitle = taskTitle;
  }
  public getTaskLabel(): string {
    return this.taskLabel;
  }
  public setTaskLabel(taskLabel: string) {
    this.taskLabel = taskLabel;
  }
  public getStartDate(): Date {
    return this.startDate;
  }
  public setStartDate(startDate: Date) {
    this.startDate = startDate;
  }
  public getDeadline(): Date {
    return this.deadline;
  }
  public setDeadline(deadline: Date) {
    this.deadline = deadline;
  }
  public getCompletedFlag(): string {
    return this.completedFlag;
  }
  public setCompletedFlag(completedFlag: string) {
    this.completedFlag = completedFlag;
  }
  public getUserId(): string {
    return this.userId;
  }
  public setUserId(userId: string) {
    this.userId = userId;
  }
  public getTaskNote(): string {
    return this.taskNote;
  }
  public setTaskNote(taskNote: string) {
    this.taskNote = taskNote;
  }

}
