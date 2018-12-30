package com.tm.controller.framework;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class BaseControllerTestUtil {

    private String resourceDir;

    // デフォルトコンストラクタ。実行時に資源のディレクトリを取得します.
    public BaseControllerTestUtil() {
        resourceDir = "src/test/resources/" + this.getClass().getName().replace(".", "/") + "/";
    }

    /**
     * テスト資源のディレクトリを返却します。
     * @return
     */
    private String getResourceDir() {
        return this.resourceDir;
    }

    /**
     * JSONファイルの内容をStringオブジェクトに書き出します。
     * @param jsonFileName
     * @return String
     * @throws IOException
     */
    public String readForObject(String jsonFileName) throws IOException {
        InputStreamReader filereader = new InputStreamReader(new FileInputStream(this.getResourceDir() + jsonFileName), StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(filereader);
        StringBuilder sb = new StringBuilder();

        String str = br.readLine();
        while (str != null) {
            sb.append(str);
            str = br.readLine();
        }

        filereader.close();
        br.close();

        return sb.toString();
    }

}
