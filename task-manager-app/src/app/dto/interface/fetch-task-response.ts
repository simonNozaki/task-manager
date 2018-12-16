import { Task } from '../../entity/task';
import { Errors } from '../common/errors';

/**
 * タスク一覧取得レスポンスDTOです.
 */
export class FetchTaskResponseDto {

  /** 
   * タスク一覧
   */
  public tasks: Task[];
  
  /**
   * エラーコード
   */
  public errors: Errors[];

  public getTasks(): Task[]|null|undefined {
    return this.tasks;
  }
  public setTasks(tasks: Task[]) {
    this.tasks = tasks;
  }
  public getErrors(): Errors[]|null|undefined {
    return this.errors;
  }
  public setErrors(errors: Errors[]) {
    this.errors = errors;
  }
}
