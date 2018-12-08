package com.tm.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * 新規登録時のIDを発番するメソッドを提供します。
 */
public class IdCounter {

    /**
     * 指定されたフォーマットでIDを自動生成します。digitsで指定した桁数でランダムな値を生成します。<br>
     * ランダムな値はアルファベット大文字小文字、および0-9の数字より構成されます。<br>
     * 例：today:2018/11/24, digits:4 -> id:181124ab12<br>
     * @param Date today
     * @param String datePattern
     * @param int digits
     * @return String id
     */
    public static String assignIdForTask(Date today, String datePattern, int digits) {
        // 日付をフォーマットします
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern(datePattern);

        // 任意の桁数で連番の数字を取得し、プレフィックスと結合します。
        return sdf.format(today).toString() + RandomStringUtils.randomAlphanumeric(digits);
    }

}
