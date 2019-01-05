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

  public getTaskTitle (): string {
    return this.taskTitle;
  }
  public setTaskTitle (taskTitle: string): void {
    this.taskTitle = taskTitle;
  }
  public getTaskLabel (): string {
    return this.taskLabel;
  }
  public setTaskLabel (taskLabel: string): void {
    this.taskLabel = taskLabel;
  }
  public getStartDate (): Date {
    return this.startDate;
  }
  public setStartDate (startDate: Date): void {
    this.startDate = startDate;
  }
  public getDeadline (): Date {
    return this.deadline;
  }
  public setDeadline (deadline: Date): void {
    this.deadline = deadline;
  }
  public getCompletedFlag (): string {
    return this.completedFlag;
  }
  public setCompletedFlag (completedFlag: string): void {
    this.completedFlag = completedFlag;
  }
  public getUserId (): string {
    return this.userId;
  }
  public setUserId (userId: string): void {
    this.userId = userId;
  }
  public getTaskNote (): string {
    return this.taskNote;
  }
  public setTaskNote (taskNote: string): void {
    this.taskNote = taskNote;
  }
}
