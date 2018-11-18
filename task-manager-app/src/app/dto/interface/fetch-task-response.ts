import { Task } from '../../entity/task';
import { Errors } from '../common/errors';

/**
 * タスク一覧取得レスポンスDTOです.
 */
export class FetchTaskResponseDto {

  /** 
   * タスク一覧
   */
  tasks: Task[];
  
  /**
   * エラーコード
   */
  private errors: Errors[];

  public get getTasks(): Task[] {
    return this.tasks;
  }
  public set setTasks(tasks: Task[]) {
    this.tasks = tasks;
  }
  public get getErrors(): Errors[] {
    return this.errors;
  }
  public set setErrors(errors: Errors[]) {
    this.errors = errors;
  }
}
