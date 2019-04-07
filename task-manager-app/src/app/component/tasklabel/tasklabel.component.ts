import { Component, OnInit } from '@angular/core';
import { CommonDeliveryService } from '../../service/common-delivery.service';
import { TaskLabelService } from '../../service/task-label.service';
import { TaskLabel } from '../../entity/task-label';
import { FormGroup, FormControl, Validators, AbstractControl } from '@angular/forms';
import { AppConst } from '../../const/app.const';
import { TaskLabelRegisterRequestDto } from '../../dto/interface/task-label-register-request.dto';
import { TaskManagerCode } from '../../codedef/task-manager-code';
import { TaskLabelRegisterResponseDto } from '../../dto/interface/task-label-register-response.dto';
import { TaskLabelFetchResponseDto } from '../../dto/interface/task-label-fetch-response.dto';
import { _ } from 'underscore';
import { Task } from '../../entity/task';
import { TaskService } from '../../service/task.service';
import { FetchTaskResponseDto } from '../../dto/interface/fetch-task-response';
import { ObjectUtil } from '../../util/object.util';

/**
 * タスクラベル処理コンポーネント
 */
@Component({
  selector: 'app-tasklabel',
  templateUrl: './tasklabel.component.html',
  styleUrls: ['./tasklabel.component.scss']
})
export class TasklabelComponent implements OnInit {

    /**
     * デフォルトコンストラクタ
     */
    constructor(private commonDeliveryService: CommonDeliveryService, private taskLabelService: TaskLabelService, private taskService: TaskService) { }

    /**
     * 利用者ID
     */ 
    public userId: string;

    /**
     * ラベルチェック結果
     */
    public labelCheckedResult: string = null;

    /**
     * タスクラベルリスト
     */
    public labels: TaskLabel[] = [];

    /**
     * タスクリスト、コンポーネント内でのタスクのマスタリスト
     */
    public tasks: Task[] = [];

    ngOnInit() {
        // サインイン時のIDを引き受ける
        this.commonDeliveryService.observableUserId.subscribe((userId: string) => {
          this.userId = userId;
        });

        // タスクラベルのリストをプロパティに設定
        this.fetchLabels(this.userId);

        // タスクのリストをプロパティに指定
        this.fetchTasks(this.userId);
    }

    /**
     * タスクラベル登録フォームグループ
     */
    public taskLabelForm: FormGroup = new FormGroup({
        taskLabelControl: new FormControl(null, [Validators.required, Validators.maxLength(AppConst.TASK_LABEL_MAX_LENGTH)])
    });

    /**
     * 利用者の保持するタスクラベルのリストを取得します。
     * @param userId: string 利用者ID
     */
    public fetchLabels(userId: string): void {
        this.taskLabelService.fetch(userId).subscribe((res: TaskLabelFetchResponseDto) => {
            // 共通データ授受サービス内の配列に格納しておく
            this.commonDeliveryService.userLabels = res.labels;
        });
    }

    /**
     * サービスクラスから、タスクの一覧を取得します.
     * @param userId: string
     */
    public fetchTasks(userId: string): void {
        this.taskService.fetchTask(userId).subscribe((res: FetchTaskResponseDto) => this.tasks = res.tasks);
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
            var label: TaskLabel = new TaskLabel();
            label.labelId = res.labelId;
            label.taskLabel = res.taskLabel;
            label.usedFlag = TaskManagerCode.TASK_LABEL_REGISTRED;
            label.userId = this.userId;
            this.commonDeliveryService.userLabels.push(label);
        });
    }

    /**
     * 入力チェックに適合していることをチェックします。
     * @returns boolean
     */
    public violateRistriction(): boolean {
        // 20桁以内であることをチェックする、ラベルの最大文字列は登録段階でのみ弾く
        var label: AbstractControl = this.taskLabelForm.get("taskLabelControl");
        if (label.hasError('required') && (label.dirty || label.touched)) {
            this.labelCheckedResult =  AppConst.TASK_LABEL_REQUIRED_VIOLATED;
            return true;
        } else if (label.hasError('maxlength') && (label.dirty || label.touched)) {
            this.labelCheckedResult =  AppConst.TASK_LABEL_LENGTH_VIOLATED;
            return true;
        }

        return false;
    }

    /**
     * ラベルでタスクをフィルターします。
     */
    public filterTasks(event, labelItem: TaskLabel | null): void {
        // nullを渡されたらリストを初期化する
        if(ObjectUtil.isNullOrUndefined(labelItem)) { 
            this.commonDeliveryService.userTasks = this.tasks;
        } else {
            // 画面から受け取ったラベルに合致するタスクのリストを生成する
            this.commonDeliveryService.userTasks = _.filter(this.tasks, (task: Task) => task.taskLabel === labelItem.taskLabel);
        }

    }

}
