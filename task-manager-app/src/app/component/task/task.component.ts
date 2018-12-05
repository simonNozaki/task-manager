import { Component, OnInit, Output, Input, EventEmitter } from '@angular/core';
import { TaskService } from '../../service/task.service';
import { FetchTaskResponseDto } from '../../dto/interface/fetch-task-response';
import { Task } from '../../entity/task';
import { TaskManagerCode } from '../../codedef/task-manager-code';
import { RegistTaskRequest } from '../../dto/interface/regist-task-request';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { AppConst } from '../../const/app.const';
import { ObjectUtil } from '../../util/object.util';
import { TaskCompleteRequestDto } from '../../dto/interface/task-complete-request.dto';
import { TaskCompleteResponseDto } from '../../dto/interface/task-complete-response.dto';
import { _ } from 'underscore';

/**
 * タスクの業務処理コンポーネント
 */
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

    /**
     * タスク登録のフォームグループ
     */
    public taskForm: FormGroup = new FormGroup({
        taskTitleControl: new FormControl(null, Validators.required),
        taskLabelControl: new FormControl("", Validators.maxLength(AppConst.TASK_LABEL_MAX_LENGTH)),
        startDateControl: new FormControl(null),
        deadlineControl: new FormControl(null),
        taskNoteControl: new FormControl("", Validators.maxLength(AppConst.TASK_NOTE_MAX_LENGTH))
    });
  
    /** 
     * バリデーションチェック結果
     */
    public checkedResult: string;

    /**
     * コンポーネント初期化時の起動処理
    */
    ngOnInit() {
      this.tasks = this.fetchTasks(this.userId);
      this.fetchTaskResponseDto = new FetchTaskResponseDto();
      this.checkedResult = "";
      this.violateRistriction();
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
        this.taskService.fetchTask(userId).subscribe(
                (res: FetchTaskResponseDto) => {
                    this.tasks = res.tasks;
                    this.fetchTaskResponseDto = res;
                }
            );
        if (ObjectUtil.isNullOrUndefined(this.fetchTaskResponseDto.getTasks())) {
            var initializedTasks: Task[] = [];
            return initializedTasks;
        }
        return this.fetchTaskResponseDto.getTasks();
    }

    /**
     * テンプレートから受け取ったタスクを登録します.
     * @param registTaskRequestDto: RegistTaskRequest
     * @returns void
    */
    public registTask(): void {
        // 入力チェックを違反していない場合、タスク登録処理を実行します
        if (!this.violateRistriction()) {
            var registTaskRequestDto: RegistTaskRequest = new RegistTaskRequest();
            // 登録リクエストDTOの生成
            registTaskRequestDto.setTaskTitle(this.taskForm.get("taskTitleControl").value);
            registTaskRequestDto.setTaskLabel(this.taskForm.get("taskLabelControl").value);
            if (ObjectUtil.isNullOrUndefined(this.taskForm.get("startDateControl").value)) {
                registTaskRequestDto.setStartDate(new Date());
            } else { 
                registTaskRequestDto.setStartDate(this.taskForm.get("startDateControl").value);
            }
            registTaskRequestDto.setDeadline(this.taskForm.get("deadlineControl").value);
            registTaskRequestDto.setTaskNote(this.taskForm.get("taskNoteControl").value);
            registTaskRequestDto.setCompletedFlag(TaskManagerCode.TASK_COMPLETED_FLAG_REGISTED);
            registTaskRequestDto.setUserId(this.userId);

            // サービスクラスを実行します。
            this.taskService.registTask(registTaskRequestDto).subscribe(
                (res: RegistTaskRequest) => console.log(res)
            );

            // 登録したタスクをリストに追加して、随時表を更新します。
            var newTask: Task = new Task();
            newTask.setTaskTitle(registTaskRequestDto.getTaskTitle());
            newTask.setTaskLabel(registTaskRequestDto.getTaskLabel());
            newTask.setTaskNote(registTaskRequestDto.getTaskNote());
            this.tasks.push(newTask);
            console.log(JSON.stringify(this.tasks));
        }

    }

    /**
     * 入力チェックに適合していることをチェックします。
     * @returns boolean
     */
    public violateRistriction(): boolean {
        // タスクタイトル。必須入力チェック
        if (this.taskForm.get('taskTitleControl').hasError('required')) {
            this.checkedResult = AppConst.TASK_TITLE_REQUIRED_VIOLATED;
            return true;
        }

        // タスクラベル。20桁以内であることをチェックする
        if (this.taskForm.get('taskLabelControl').hasError('maxlength')) {
            this.checkedResult =  AppConst.TASK_LABEL_LENGTH_VIOLATED;
            return true;
        }

        // タスクメモ。200文字以内であることをチェックする
        if (this.taskForm.get('taskNoteControl').hasError('maxlength')) {
            this.checkedResult =  AppConst.TASK_NOTE_LENGTH_VIOLATED;
            return true;
        }
        console.log("return true");

        return false;
    }

    /**
     * タスクを完了します。
     * @param taskId: string
     * @returns void
     */
    public complete(taskId: string): void {
        // リクエストDTOの初期化
        var taskCompleteRequestDto: TaskCompleteRequestDto = new TaskCompleteRequestDto();
        taskCompleteRequestDto.setTaskId(taskId);

        // サービスクラスの実行。レスポンスが帰ってきてたら、タスクのリストから削除。
        this.taskService.complete(taskCompleteRequestDto).subscribe((res: TaskCompleteResponseDto) => {
            if (!ObjectUtil.isNullOrUndefined(res.taskId)) {
                  console.log(JSON.stringify(res.taskId));
                  // 完了したタスクを抽出
                  var completedTask: Task = _.where(this.tasks, {taskId : res.taskId});
                  // 完了したタスクのINDEXを取得
                  var index: number = _.indexOf(this.tasks, completedTask);
                  // タスクのリストから削除
                  this.tasks.splice(index, 1);
                  console.log(completedTask, index, JSON.stringify(this.tasks));
            }
        });
    }


}
