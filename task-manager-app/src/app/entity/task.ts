/**
 * タスクモデルの汎用エンティティクラスです.
 */
export class Task {

  /**
   * タスクID
   */
  private taskId: string;
  
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
  public taskNote: string;

  public get getTaskId(): string {
    return this.taskId;
  }
  public set setTaskId(taskId: string) {
    this.taskId = taskId;
  }
  public get getTaskTitle(): string {
    return this.taskTitle;
  }
  public set setTaskTitle(taskTitle: string) {
    this.taskTitle = taskTitle;
  }
  public get getTaskLabel(): string {
    return this.taskLabel;
  }
  public set setTaskLabel(taskLabel: string) {
    this.taskLabel = taskLabel;
  }
  public get getStartDate(): Date {
    return this.startDate;
  }
  public set setStartDate(startDate: Date) {
    this.startDate = startDate;
  }
  public get getDeadline(): Date {
    return this.deadline;
  }
  public set setDeadline(deadline: Date) {
    this.deadline = deadline;
  }
  public get getCompletedFlag(): string {
    return this.completedFlag;
  }
  public set setCompletedFlag(completedFlag: string) {
    this.completedFlag = completedFlag;
  }
  public get getUserId(): string {
    return this.userId;
  }
  public set setUserId(userId: string) {
    this.userId = userId;
  }
  public get getTaskNote(): string {
    return this.taskNote;
  }
  public set setTaskNote(taskNote: string) {
    this.taskNote = taskNote;
  }

}
