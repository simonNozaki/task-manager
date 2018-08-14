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
}
