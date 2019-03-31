import { Component, OnInit } from '@angular/core';
import { CommonDeliveryService } from '../../service/common-delivery.service';
import { Router } from '@angular/router';
import { TaskService } from '../../service/task.service';
import { ObjectUtil } from '../../util/object.util';
import { ServiceConst } from '../../const/service-const';
import { Task } from '../../entity/task';
import { _ } from 'underscore';
import { TaskLabel } from '../../entity/task-label';
import { TaskLabelRelation } from '../../dto/task-label-relation';
import { MAT_RIPPLE_GLOBAL_OPTIONS } from '@angular/material';
import { CollectionUtil } from '../../util/collection-util';

/**
 * タスクタイムラインコンポーネント
 */
@Component({
  selector: 'app-taskline',
  templateUrl: './taskline.component.html',
  styleUrls: ['./taskline.component.scss']
})
export class TasklineComponent implements OnInit {

    constructor(private taskService: TaskService, private router: Router, private commonDeliveryService: CommonDeliveryService) { }

    /**
     * 利用者ID
     */ 
    public userId: string;

    /**
     * タスクリスト 
     */ 
    public tasks: Task[] = [];

    /**
     * タスクラベル
     */
    public labels: TaskLabel[] = [];

    /**
     * タスクのラベルと一覧の関係配列オブジェクト
     */
    public relations: TaskLabelRelation[] = [];

    /**
     * コンポーネント初期化時の起動処理
    */
    ngOnInit() {
        // リダイレクトされたときに、別のコンポーネントから動的に利用者IDを受け取ります。
        this.commonDeliveryService.observableUserId.subscribe((userId: string) => this.userId = userId);

        // 利用者IDが正常に設定されていない場合、リダイレクトしてアクセスを拒否する
        if (ObjectUtil.isNullOrUndefined(this.userId)) {
            this.router.navigateByUrl(ServiceConst.BASE_SLASH + ServiceConst.URL_WEB_USER_SIGNIN);
        }
        
        // タスクのリストをプロパティに設定
        this.tasks = this.commonDeliveryService.userTasks;

        // ラベルをプロパティに設定
        this.labels = this.commonDeliveryService.userLabels;

        // ラベル別にソートしたプロジェクトタスクの関係性を設定
        this.setProjects();

        console.log(JSON.stringify(this.relations))
    }

    /**
     * プロジェクトの名前およびタスクの関係を設定します。
     */
    public setProjects(): void{
        // ユーザがラベルを登録していることを確認する
        if(CollectionUtil.hasItems(this.commonDeliveryService.userLabels) && CollectionUtil.hasItems(this.commonDeliveryService.userTasks)){
            for(var label of this.commonDeliveryService.userLabels){
                var filteredTasks: Task[] = [];
                for(var task of this.tasks){
                    if(label.taskLabel === task.taskLabel){
                        filteredTasks.push(task);
                    }
                }

                var relation: TaskLabelRelation = new TaskLabelRelation();
                relation.taskLabel = label.taskLabel;
                relation.tasks = filteredTasks;

                // フィルターしたタスクの表とラベル名をリレーションに設定する
                this.relations.push(relation);
            }
        }
    }
}
