import { Task } from "../entity/task";

/**
 * 日付を分解した文字列をタスクデータ
 */
export class DateDetailedTask extends Task{

    /**
     * 
     */
    public startDateYear: string;

    /**
     * 
     */
    public startDateMonth: string;

    /**
     * 
     */
    public startDateDate: string;

    /**
     * 
     */
    public deadlineYear: string;

    /**
     * 
     */
    public deadlineMonth: string;

    /**
     * 
     */
    public deadlineDate: string;
}
