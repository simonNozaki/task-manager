import { Component, OnInit, Output, Input, EventEmitter } from '@angular/core';
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


  /**
   * タスク取得DTO
  */
  public fetchTaskResponseDto: FetchTaskResponseDto = new FetchTaskResponseDto();
  
  /**
   * タスクリスト 
   */ 
  @Input() public tasks: Task[] = this.fetchTaskResponseDto.getTasks();
  
  /**
   * タスク登録DTO
   */ 
  public registTaskRequestDto: RegistTaskRequest;
  
  /**
   * 利用者ID 
   */ 
  private userId = '10001000';

  /** 
   * 入力値の変化を検知してバインドするイベントエミッタ
   */
  @Output() emitter = new EventEmitter<Task[]>();

  /** 
   * タスクタイトル
   */
  public taskTitle: string;
  
  /**
   * タスクラベル
   */
  public  taskLabel: string;
  
  /** 
   * 開始日
   */
  public startDate: Date;
  
  /** 
   * 期限日
   */
  public deadline: Date;
      
  /** 
   * タスクメモ 
   */
  public taskNote: string;

  /**
   * コンポーネント初期化時の起動処理
  */
  ngOnInit() {
    this.registTaskRequestDto = new RegistTaskRequest();
    this.fetchTaskResponseDto.setTasks(this.fetchTasks(this.userId));
  }

  /**
   * 入力されたタスクの変化を検知するハンドラ
  */
  change(tasks): void {
    this.tasks = tasks;
    this.emitter.emit(this.tasks);
  }

  /**
   * サービスクラスから、タスクの一覧を取得します.
  */
  fetchTasks(userId): Task[] {
    this.taskService.fetchTask(userId)
        .subscribe(
            (res: FetchTaskResponseDto) => this.fetchTaskResponseDto.setTasks(res.getTasks()),
            () => console.log(this.tasks),
            () => console.log(this.fetchTaskResponseDto)
        );
    return this.fetchTaskResponseDto.getTasks();
  }

  /**
   * テンプレートから受け取ったタスクをDBに非同期で登録します.
  */
  registTask(registTaskRequestDto: RegistTaskRequest): void {
    // 登録リクエストDTOの生成
    this.registTaskRequestDto.setTaskTitle(this.taskTitle);
    this.registTaskRequestDto.setTaskLabel(this.taskLabel);
    this.registTaskRequestDto.setStartDate(this.startDate);
    this.registTaskRequestDto.setDeadline(this.deadline);
    this.registTaskRequestDto.setTaskNote(this.taskNote);
    this.registTaskRequestDto.setCompletedFlag(TaskManagerCode.TASK_COMPLETED_FLAG_REGISTED);
    this.registTaskRequestDto.setUserId(this.userId);

    console.log(this.registTaskRequestDto);

    this.taskService.registTask(registTaskRequestDto)
      .subscribe(
        () => console.log(registTaskRequestDto)
      );
  }
}
