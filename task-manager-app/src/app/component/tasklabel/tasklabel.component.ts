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
    constructor(private commonDeliveryService: CommonDeliveryService, private taskLabelService: TaskLabelService) { }

    /**
     * 利用者ID
     */ 
    public userId: string;

    /** 
     * タスクチェック結果
     */
    public checkedResult: string;

    /**
     * ラベルチェック結果
     */
    public labelCheckedResult: string;

    /**
     * タスクラベルリスト
     */
    public labels: TaskLabel[];

    ngOnInit() {
        // サインイン時のIDを引き受ける
        this.commonDeliveryService.observableUserId.subscribe((userId: string) => {
          this.userId = userId;
        });

        // タスクラベルのリストをプロパティに設定
        this.fetchLabels(this.userId);
        this.checkedResult = "";
        console.log(JSON.stringify(this.labels));
        console.log(JSON.stringify(this.commonDeliveryService.userLabels));
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
            console.log(JSON.stringify(res));
            
            // 共通データ授受サービス内の配列に格納しておく
            this.commonDeliveryService.userLabels = res.labels;
        });
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
            // this.labels.push(label);
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

}
