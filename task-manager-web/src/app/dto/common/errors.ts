/**
 * 共通レスポンスエラーコードDTOです.
 */
export class Errors {

  /** タスクタイトル */
  private taskTitle: string;
  /** エラーコードリスト */
  private codes: string[];

  public getCodes(): string[] {
    return this.codes;
  }
  public setCodes(codes: string[]) {
    this.codes = codes;
  }
  public getTaskTitle(): string {
    return this.taskTitle;
  }
  public setTaskTitle(taskTitle: string) {
    this.taskTitle = taskTitle;
  }
}
