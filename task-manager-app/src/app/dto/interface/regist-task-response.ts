import { Errors } from '../common/errors';

/**
 * タスク登録レスポンスDTOです.
 */
export class RegistTaskResponse {
  /** タスクID */
   private taskId: string;
  /** タスクタイトル */
  private taskTitle: string;
  /** エラー結果 */
  private errors: Errors[];

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
  public getErrors(): Errors[] {
    return this.errors;
  }
  public setErrors(errors: Errors[]) {
    this.errors = errors;
  }
}
