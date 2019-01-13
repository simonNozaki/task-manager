/**
 * 日付処理ユーティリティ。
 */
export class DateUtil {

    /**
     * 文字列を解析して日付フォーマットであることを確認します。
     */
    private static isDateFormat(operand: string | null): boolean {

        // 変換できることを確認
        if (Number.isNaN(Date.parse(operand))) {
            return false;
        }
        return true;
    };

    /**
     * ２つの日付を比較し、第一引数 > 第二引数の関係となっていることを確認します。
     * @param subject 非検査対象
     * @param comparison 非検査対象と比較する比較対象
     */
    public static isForwardFromComparison(subject: string, comparison: string): boolean{
        // 比較する２つの引数が日付フォーマットに変換できない場合はfalse
        if(!this.isDateFormat(subject) || !this.isDateFormat(comparison)){
            return false;
        }

        var subjectTime: number = Date.parse(subject);
        var comparisonTime: number = Date.parse(comparison);

        //  日付の比較
        if(subjectTime < comparisonTime){
            return false;
        }
        return true;
    }
}
