import { Component, OnInit, Output, Input, EventEmitter } from '@angular/core';
import { TaskService } from '../../service/task.service';
import { FetchTaskResponseDto } from '../../dto/interface/fetch-task-response';
import { Task } from '../../entity/task';
import { TaskManagerCode } from '../../codedef/task-manager-code';
import { RegistTaskRequest } from '../../dto/interface/regist-task-request';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { TypedFormControl } from '../../util/typed-form-control.util';
import { AppConst } from '../../const/app.const';
 
@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {

  //-----------------------------
  // コンポーネント内プロパティ
  //-----------------------------

  /** 
   * デフォルトコンストラクタ
   */
  constructor(private taskService: TaskService) { }

  /**
   * タスク取得DTO
  */
  public fetchTaskResponseDto: FetchTaskResponseDto;
  
  /**
   * タスクリスト 
   */ 
  @Input()
  public tasks: Task[];
  
  /**
   * 利用者ID 
   */ 
  private userId = '10001000';

  /** 
   * 入力値の変化を検知してバインドするイベントエミッタ
   */
  @Output()
  public emitter = new EventEmitter<Task[]>();

  public taskTitleControl:FormControl = new FormControl(null, Validators.required);
  
  public  taskLabelControl:FormControl = new FormControl("", Validators.maxLength(AppConst.TASK_LABEL_MAX_LENGTH));
  
  public startDateControl:FormControl = new FormControl(null);
  
  public deadlineControl:FormControl = new FormControl(null);
      
  public taskNoteControl:FormControl = new FormControl("", Validators.maxLength(AppConst.TASK_NOTE_MAX_LENGTH));

  /**
   * タスク登録のフォームグループ
   */
  public taskForm: FormGroup = new FormGroup({
    taskTitleControl: this.taskTitleControl,
    taskLabelControl: this.taskLabelControl,
    startDateControl: this.startDateControl,
    deadlineControl: this.deadlineControl,
    taskNoteControl: this.taskNoteControl
  });
  
  /** 
   * バリデーションチェック結果
   */
  private checkedResult: string;

  /**
   * コンポーネント初期化時の起動処理
  */
  ngOnInit() {
    this.tasks = this.fetchTasks(this.userId);
    this.fetchTaskResponseDto = new FetchTaskResponseDto();
    this.checkedResult = "";
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
            (res: FetchTaskResponseDto) => {
              this.tasks = res.tasks;
              this.fetchTaskResponseDto = res;
            }
        );
    return this.fetchTaskResponseDto.getTasks();
  }

  /**
   * テンプレートから受け取ったタスクを登録します.
   * @param registTaskRequestDto: RegistTaskRequest
   * @returns void
  */
  public registTask(): void {
    console.log(this.checkedResult);
    // 入力チェックを違反していない場合、タスク登録処理を実行します
    if (!this.violateRistriction()) {
      var registTaskRequestDto: RegistTaskRequest = new RegistTaskRequest();
      // 登録リクエストDTOの生成
      registTaskRequestDto.setTaskTitle(this.taskTitleControl.value);
      registTaskRequestDto.setTaskLabel(this.taskLabelControl.value);
      registTaskRequestDto.setStartDate(this.startDateControl.value);
      registTaskRequestDto.setDeadline(this.deadlineControl.value);
      registTaskRequestDto.setTaskNote(this.taskNoteControl.value);
      registTaskRequestDto.setCompletedFlag(TaskManagerCode.TASK_COMPLETED_FLAG_REGISTED);
      registTaskRequestDto.setUserId(this.userId);
    
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
    console.log("入力チェックを開始", this.checkedResult, this.taskLabelControl.errors, this.taskTitleControl.errors);

    // タスクタイトル。必須入力チェック
    if (this.taskTitleControl.invalid && (this.taskTitleControl.dirty || this.taskTitleControl.touched)) {
      this.checkedResult = AppConst.TASK_TITLE_REQUIRED_VIOLATED;
      console.log("必須入力に失敗", this.checkedResult);
      return true;
    }

    // タスクラベル。20桁以内であることをチェックする
    if (this.taskLabelControl.invalid && (this.taskLabelControl.dirty || this.taskLabelControl.touched)) {
      this.checkedResult =  AppConst.TASK_LABEL_LENGTH_VIOLATED;
      console.log("入力最大値を違反");
      return true;
    }

    // タスクメモ。200文字以内であることをチェックする
    if (this.taskNoteControl.invalid && (this.taskNoteControl.dirty || this.taskNoteControl.touched)) {
      this.checkedResult =  AppConst.TASK_NOTE_LENGTH_VIOLATED;
      console.log("入力最大値を違反");
      return true;
    }

    console.log("return true");

    return false;
  }


}
