import { Component, OnInit } from '@angular/core';
import { TaskService } from '../../service/task.service';
import { FetchTaskResponseDto } from '../../dto/interface/fetch-task-response';
import { Task } from '../../entity/task';
import { TaskManagerCode } from '../../codedef/task-manager-code';
import { RegistTaskRequest } from '../../dto/interface/regist-task-request';
import { FormGroup, Validators, FormControl, AbstractControl } from '@angular/forms';
import { AppConst } from '../../const/app.const';
import { ObjectUtil } from '../../util/object.util';
import { TaskCompleteRequestDto } from '../../dto/interface/task-complete-request.dto';
import { TaskCompleteResponseDto } from '../../dto/interface/task-complete-response.dto';
import { _ } from 'underscore';
import { CommonDeliveryService } from '../../service/common-delivery.service';
import { Router } from '@angular/router';
import { ServiceConst } from '../../const/service-const';
import { TaskLabel } from '../../entity/task-label';
import { TaskLabelService } from '../../service/task-label.service';
import { TaskLabelFetchResponseDto } from '../../dto/interface/task-label-fetch-response.dto';
import { RegistTaskResponse } from '../../dto/interface/regist-task-response';
import { TaskLabelRegisterRequestDto } from '../../dto/interface/task-label-register-request.dto';
import { TaskLabelRegisterResponseDto } from '../../dto/interface/task-label-register-response.dto';
import { DateUtil } from '../../util/date-util';

/**
 * タスクの業務処理コンポーネント
 */
@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.scss']
})
export class TaskComponent implements OnInit {

    /** 
     * デフォルトコンストラクタ
     */
    constructor(private taskService: TaskService, private router: Router, private commonDeliveryService: CommonDeliveryService,
        private taskLabelService: TaskLabelService) {}

    /**
     * 利用者ID
     */ 
    public userId: string;

    /**
     * コンポーネント初期化時の起動処理
    */
    ngOnInit() {
        // リダイレクトされたときに、別のコンポーネントから動的に利用者IDを受け取ります。
        this.commonDeliveryService.observableUserId.subscribe((userId: string) => {
            this.userId = userId;
        });

        // 利用者IDが正常に設定されていない場合、リダイレクトしてアクセスを拒否する
        if (ObjectUtil.isNullOrUndefined(this.userId)) {
            this.router.navigateByUrl(ServiceConst.BASE_SLASH + ServiceConst.URL_WEB_USER_SIGNIN);
        }
        
        // タスクのリストをプロパティに設定
        this.fetchTasks(this.userId);

        // タスクラベルのリストをプロパティに設定
        this.fetchLabels(this.userId);
        this.checkedResult = "";

        console.log(this.userId);
    }

    /**
     * タスクリスト 
     */ 
    public tasks: Task[];

    /**
     * タスクラベルリスト
     */
    public labels: TaskLabel[];
    
    /**
     * タスク登録のフォームグループ
     */
    public taskForm: FormGroup = new FormGroup({
        taskTitleControl: new FormControl(null, [Validators.required, Validators.maxLength(AppConst.TASK_TITLE_MAX_LENGTH)]),
        taskLabelControl: new FormControl(""),
        startDateControl: new FormControl(null),
        deadlineControl: new FormControl(null),
        taskNoteControl: new FormControl("", Validators.maxLength(AppConst.TASK_NOTE_MAX_LENGTH))
    });

    /**
     * タスクラベル登録フォームグループ
     */
    public taskLabelForm: FormGroup = new FormGroup({
        taskLabelControl: new FormControl(null, [Validators.required, Validators.maxLength(AppConst.TASK_LABEL_MAX_LENGTH)])
    });
  
    /** 
     * タスクチェック結果
     */
    public checkedResult: string;

    /**
     * ラベルチェック結果
     */
    public labelCheckedResult: string;

    /**
     * サービスクラスから、タスクの一覧を取得します.
     * @param userId: string
     * @returns Task[]
    */
    public fetchTasks(userId: string): void {
        this.taskService.fetchTask(userId).subscribe((res: FetchTaskResponseDto) => {
                    this.tasks = res.tasks;
                }
            );
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
                registTaskRequestDto.setStartDate(DateUtil.formatDateYMDWithSlash(new Date()));
            } else { 
                registTaskRequestDto.setStartDate(this.taskForm.get("startDateControl").value);
            }
            registTaskRequestDto.setDeadline(this.taskForm.get("deadlineControl").value);
            registTaskRequestDto.setTaskNote(this.taskForm.get("taskNoteControl").value);
            registTaskRequestDto.setCompletedFlag(TaskManagerCode.TASK_COMPLETED_FLAG_REGISTED);
            registTaskRequestDto.setUserId(this.userId);

            // サービスクラスを実行します。
            this.taskService.registTask(registTaskRequestDto).subscribe((res: RegistTaskResponse) => {
                console.log(JSON.stringify(res));
            });

            // 登録したタスクをリストに追加して、随時表を更新します。
            var newTask: Task = new Task();
            newTask.setTaskTitle(registTaskRequestDto.getTaskTitle());
            newTask.setTaskLabel(registTaskRequestDto.getTaskLabel());
            newTask.setTaskNote(registTaskRequestDto.getTaskNote());
            newTask.setStartDate(registTaskRequestDto.getStartDate());
            newTask.setDeadline(registTaskRequestDto.getDeadline());
            this.tasks.push(newTask);
        }
    }

    /**
     * 入力チェックに適合していることをチェックします。
     * @returns boolean
     */
    public violateRistriction(): boolean {
        // タスクタイトル。必須入力チェック
        var taskTitle: AbstractControl = this.taskForm.get("taskTitleControl");
        if (taskTitle.hasError('required') && (taskTitle.dirty || taskTitle.touched)) {
            this.checkedResult = AppConst.TASK_TITLE_REQUIRED_VIOLATED;
            return true;
        } else if (taskTitle.hasError('required') && (taskTitle.dirty || taskTitle.touched)) {
            this.checkedResult = AppConst.TASK_TITLE_LENGTH_VIOLATED;
            return true;
        }

        // タスクラベル。20桁以内であることをチェックする、ラベルの最大文字列は登録段階でのみ弾く
        var label: AbstractControl = this.taskLabelForm.get("taskLabelControl");
        if (label.hasError('required') && (label.dirty || label.touched)) {
            this.labelCheckedResult =  AppConst.TASK_LABEL_REQUIRED_VIOLATED;
            return true;
        } else if (label.hasError('maxlength') && (label.dirty || label.touched)) {
            this.labelCheckedResult =  AppConst.TASK_LABEL_LENGTH_VIOLATED;
            return true;
        }

        // タスクメモ。200文字以内であることをチェックする
        var note: AbstractControl = this.taskForm.get("taskNoteControl");
        if (note.hasError('maxlength') && (note.dirty || note.touched)) {
            this.checkedResult =  AppConst.TASK_NOTE_LENGTH_VIOLATED;
            return true;
        }

        // 終了日。開始日との関係が正常であることを確認する
        var deadline: string = this.taskForm.get("deadlineControl").value;
        var startdate: string = this.taskForm.get("startDateControl").value;
        if(!ObjectUtil.isNullOrUndefined(deadline) && DateUtil.isForwardFromComparison(deadline, startdate)) {
            this.checkedResult = AppConst.DEADLINE_SET_BEFORE_START_DATE;
            return true;
        }

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
                  // 完了したタスクを抽出
                  var completedTask: Task = _.where(this.tasks, {taskId : res.taskId});
                  // 完了したタスクのINDEXを取得
                  var index: number = _.indexOf(this.tasks, completedTask);
                  // タスクのリストから削除
                  this.tasks.splice(index, 1);
            }
        });
    }

    /**
     * 利用者の保持するタスクラベルのリストを取得します。
     * @param userId: string 利用者ID
     */
    public fetchLabels(userId: string): void {
        this.taskLabelService.fetch(userId).subscribe((res: TaskLabelFetchResponseDto) => {
            console.log(JSON.stringify(res));
            this.labels = res.labels;
        })
    }

    /**
     * ラベルを新規登録します。
     */
    public registerLabel(): void {
        // リクエストオブジェクトを初期化
        var req: TaskLabelRegisterRequestDto = new TaskLabelRegisterRequestDto();
        req.taskLabel = this.taskLabelForm.get("taskLabelControl").value;
        req.usedFlag = TaskManagerCode.TASK_LABEL_REGISTRED;
        req.userId = this.userId;

        // 正常に登録できたら、リストに追加する
        this.taskLabelService.registerLabel(req).subscribe((res: TaskLabelRegisterResponseDto) => {
            console.log(JSON.stringify(res));
            var label: TaskLabel = new TaskLabel();
            label.labelId = res.labelId;
            label.taskLabel = res.taskLabel;
            label.usedFlag = TaskManagerCode.TASK_LABEL_REGISTRED;
            label.userId = this.userId;
            this.labels.push(label);
        });
    }

}
