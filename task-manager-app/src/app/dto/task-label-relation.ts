import { Task } from "../entity/task";

/**
 * ラベル別にタスクのリストを管理するデータクラス
 */
export class TaskLabelRelation {

    /**
     * タスクラベル名
     */
    public taskLabel: string;

    /**
     * タスク一覧
     */
    public tasks: Task[]
}
