import { Component, OnInit } from '@angular/core';
import { TaskService } from '../../service/task.service';
import { FetchTaskResponseDto } from '../../dto/interface/fetch-task-response';
import { Task } from '../../entity/task';
import { TaskManagerCode } from '../../codedef/task-manager-code';
import { RegistTaskRequest } from '../../dto/interface/regist-task-request';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {

/** サービスクラス、およびルートのデフォルトコンストラクタ */
constructor(private taskService: TaskService) { }

  /** テンプレート内で参照する変数を初期化します. */
  // タスク取得DTO
  fetchTaskResponseDto: FetchTaskResponseDto = new FetchTaskResponseDto();
  // タスクリスト
  tasks: Task[] = this.fetchTaskResponseDto.getTasks();
  // タスク登録DTOを初期化します.
  registTaskRequestDto: RegistTaskRequest = new RegistTaskRequest();
  // 利用者ID
  private userId = '10001000';

  /** コンポーネント初期化時の起動処理 */
  ngOnInit() {
    this.fetchTasks(this.userId);
  }

  /** サービスクラスから、タスクの一覧を取得します. */
  fetchTasks(userId): void {
    this.taskService.fetchTask(userId)
    .subscribe(
      fetchTaskResponseDto => this.fetchTaskResponseDto = fetchTaskResponseDto,
      () => console.log(this.tasks),
      () => console.log(this.fetchTaskResponseDto)
    );
  }

  /** テンプレートから受け取ったタスクをDBに非同期で登録します. */
  registTask(registTaskRequestDto): void {
    /** 完了フラグ、利用者IDにそれぞれ固定値を設定します. */
    this.registTaskRequestDto.setCompletedFlag(TaskManagerCode.TASK_COMPLETED_FLAG_REGISTED);
    this.registTaskRequestDto.setUserId(this.userId);
    this.taskService.registTask(registTaskRequestDto)
    .subscribe(
      () => console.log(registTaskRequestDto)
    );
  }
}
