import { DatePipe } from '@angular/common';

/**
 * タスクモデルの汎用エンティティクラスです.
 */
export class Task {

   /** タスクID */
   private taskId: string;
  /** タスクタイトル */
  private taskTitle: string;
  /** 開始日 */
  private startDate: DatePipe;
  /** 期限日 */
  private deadline: DatePipe;
  /** 完了フラグ */
  private completedFlag: string;
  /** 利用者ID */
  private userId: string;
  /** タスクメモ */
  private taskNote: string;

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
  public getStartDate(): DatePipe {
    return this.startDate;
  }
  public setStartDate(startDate: DatePipe) {
    this.startDate = startDate;
  }
  public getDeadline(): DatePipe {
    return this.deadline;
  }
  public setDeadline(deadline: DatePipe) {
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
