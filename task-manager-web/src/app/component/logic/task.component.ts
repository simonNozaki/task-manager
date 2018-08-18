import { FetchTaskResponseDto } from '../../dto/interface/fetch-task-response';
import { TaskService } from '../../service/task.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-task-list',
  templateUrl: '../template/task.component.html'
})

/**
 * タスク処理の実装コンポーネントです.
 */
export class TaskComponent implements OnInit {

  /** サービスクラス、およびルートのデフォルトコンストラクタ */
  constructor(private taskService: TaskService,
    private route: ActivatedRoute) { }

  /** テンプレート内で参照する変数を初期化します. */
  // タスク取得DTO
  public fetchTaskResponseDto: FetchTaskResponseDto;
  // 利用者ID
  public userId = '10001000 ';

  /** コンポーネント初期化時の起動処理 */
  ngOnInit() {
    this.fetchTasks(this.userId);
  }

  /** サービスクラスから、タスクの一覧を取得します. */
  fetchTasks(userId): void {
    this.taskService.fetchTask(userId)
      .subscribe(
        fetchTaskResponseDto => this.fetchTaskResponseDto = fetchTaskResponseDto
      );
  }

}
