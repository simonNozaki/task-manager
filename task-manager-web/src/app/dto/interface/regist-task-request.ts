import { DatePipe } from '@angular/common';

/**
 * タスク登録レスポンスDTOです.
 */
export class RegistTaskRequest {
  /** タスクID */
   private taskId: string;
  /** タスクタイトル */
  private taskTitle: string;
}
