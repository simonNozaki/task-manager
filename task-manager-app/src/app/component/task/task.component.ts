import { Component, OnInit, Output, Input, EventEmitter } from '@angular/core';
import { TaskService } from '../../service/task.service';
import { FetchTaskResponseDto } from '../../dto/interface/fetch-task-response';
import { Task } from '../../entity/task';
import { TaskManagerCode } from '../../codedef/task-manager-code';
import { RegistTaskRequest } from '../../dto/interface/regist-task-request';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { TypedFormControl } from '../../util/typed-form-control.util';
import { AppConst } from '../../const/app.const';

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

  public taskTitle:TypedFormControl<string> = new TypedFormControl<string>(null, Validators.required);
  
  public  taskLabel:TypedFormControl<string> = new TypedFormControl<string>(null, Validators.maxLength(AppConst.TASK_LABEL_MAX_LENGTH));
  
  public startDate: TypedFormControl<Date> = new TypedFormControl<Date>(null);
  
  public deadline:TypedFormControl<Date> = new TypedFormControl<Date>(null);
      
  public taskNote:TypedFormControl<string> = new TypedFormControl<string>(null, Validators.maxLength(AppConst.TASK_NOTE_MAX_LENGTH));

  /** 
   * バリデーションチェック結果
   */
  public checkedResult: string = null;

  taskForm: FormGroup = new FormGroup({
    taskTitle: this.taskTitle,
    taskLabel: this.taskLabel,
    startDate: this.startDate,
    deadline: this.deadline,
    taskNote: this.taskNote
  });

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
   * @param userId: string
   * @returns Task[]
  */
  public fetchTasks(userId): Task[] {
    this.taskService.fetchTask(userId)
        .subscribe(
            (res: FetchTaskResponseDto) => this.fetchTaskResponseDto.setTasks(res.getTasks()),
            () => console.log(this.tasks),
            () => console.log(this.fetchTaskResponseDto)
        );
    return this.fetchTaskResponseDto.getTasks();
  }

  /**
   * テンプレートから受け取ったタスクを登録します.
   * @param registTaskRequestDto: RegistTaskRequest
   * @returns void
  */
  public registTask(registTaskRequestDto: RegistTaskRequest): void {
    console.log(this.checkedResult);
    // 入力チェックを違反していない場合、タスク登録処理を実行します
    if (!this.violateRistriction()) {
      // 登録リクエストDTOの生成
      this.registTaskRequestDto.setTaskTitle(this.taskTitle.getValue());
      this.registTaskRequestDto.setTaskLabel(this.taskLabel.getValue());
      this.registTaskRequestDto.setStartDate(this.startDate.getValue());
      this.registTaskRequestDto.setDeadline(this.deadline.getValue());
      this.registTaskRequestDto.setTaskNote(this.taskNote.getValue());
      this.registTaskRequestDto.setCompletedFlag(TaskManagerCode.TASK_COMPLETED_FLAG_REGISTED);
      this.registTaskRequestDto.setUserId(this.userId);
    
      // サービスクラスを実行
      this.taskService.registTask(registTaskRequestDto)
      .subscribe(
        () => console.log(registTaskRequestDto)
      );
    }

  }

  /**
   * 入力チェックに適合していることをチェックします。
   * @returns string
   */
  public violateRistriction(): boolean {
    console.log("入力チェックを開始", this.checkedResult);

    // タスクタイトル。必須入力チェック
    if (this.taskTitle.invalid && (this.taskTitle.dirty || this.taskTitle.touched)) {
      console.log("必須入力に失敗", this.checkedResult);
      this.checkedResult = AppConst.TASK_TITLE_REQUIRED_VIOLATED;
      return false;
    }

    // タスクラベル。20桁以内であることをチェックする
    if (this.taskLabel.invalid && (this.taskLabel.dirty || this.taskLabel.touched)) {
      console.log("入力最大値を違反");
      this.checkedResult =  AppConst.TASK_LABEL_LENGTH_VIOLATED;
      return false;
    }

    // タスクメモ。200文字以内であることをチェックする
    if (this.taskNote.invalid && (this.taskNote.dirty || this.taskNote.touched)) {
      console.log("入力最大値を違反");
      this.checkedResult =  AppConst.TASK_NOTE_LENGTH_VIOLATED;
      return false;
    }

    return true;
  }

}
