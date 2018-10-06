import { DatePipe } from '@angular/common';

/**
 * タスク登録リクエストDTOです.
 */
export class RegistTaskRequest {
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
}
