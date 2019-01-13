package com.tm.controller.task;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.tm.controller.framework.BaseControllerTestUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("unit")
public class TaskRestControllerTest extends BaseControllerTestUtil {

	@Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private String targetUrl = "http://localhost:18080/api/v1/task/regist";

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /**
     * 正常系、有効なタスク情報が登録できること
     * 001
     */
    @Test
    @Sql({"classpath:/com/tm/controller/framework/reset_db.sql", "classpath:/com/tm/controller/framework/task_common_data.sql"})
    public void test001() throws Exception {
    	String testMethodName = new Throwable().getStackTrace()[0].getMethodName();
        String body = super.readForObject(testMethodName + ".json");

        // POSTリクエストの実施
        ResultActions result = mockMvc.perform(post(targetUrl)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(body));

        // 結果の検証
        result.andExpect(status().is(HttpStatus.CREATED.value()))
            .andExpect(jsonPath("$.taskTitle").value("testtask"))
            .andExpect(jsonPath("$.errors").doesNotExist());
    }

    /**
     * 異常系、規定の長さ以上のタスクタイトルを弾く
     * 002
     */
    @Test
    public void test002() throws Exception {
    	String testMethodName = new Throwable().getStackTrace()[0].getMethodName();
        String body = super.readForObject(testMethodName + ".json");

        // POSTリクエストの実施
        ResultActions result = mockMvc.perform(post(targetUrl)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(body));

        // アサーションの実施
        result.andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
              .andExpect(jsonPath("$.errors.codes[0]").value("ERR220002"));
    }

    /**
     * 異常系、規定の長さ以上のタスクラベルを弾く
     * 003
     */
    @Test
    public void test003() throws Exception {
    	String testMethodName = new Throwable().getStackTrace()[0].getMethodName();
        String body = super.readForObject(testMethodName + ".json");

        // POSTリクエストの実施
        ResultActions result = mockMvc.perform(post(targetUrl)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(body));

        // アサーションの実施
        result.andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
              .andExpect(jsonPath("$.errors.codes[0]").value("ERR230001"));
    }

    /**
     * 異常系、規定の長さ以上のタスクメモを弾く
     * 004
     */
    @Test
    public void test004() throws Exception {
    	String testMethodName = new Throwable().getStackTrace()[0].getMethodName();
        String body = super.readForObject(testMethodName + ".json");

        // POSTリクエストの実施
        ResultActions result = mockMvc.perform(post(targetUrl)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(body));

        // アサーションの実施
        result.andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
              .andExpect(jsonPath("$.errors.codes[0]").value("ERR240001"));
    }

    /**
     * 異常系、10桁でない利用者IDを弾く
     * 005
     */
    @Test
    public void test005() throws Exception {
    	String testMethodName = new Throwable().getStackTrace()[0].getMethodName();
        String body = super.readForObject(testMethodName + ".json");

        // POSTリクエストの実施
        ResultActions result = mockMvc.perform(post(targetUrl)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(body));

        // アサーションの実施
        result.andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
              .andExpect(jsonPath("$.errors.codes[0]").value("ERR110003"));
    }

    /**
     * 異常系、空のリクエストを弾く
     * 006
     */
    @Test
    public void test006() throws Exception {
    	String testMethodName = new Throwable().getStackTrace()[0].getMethodName();
        String body = super.readForObject(testMethodName + ".json");

        // POSTリクエストの実施
        ResultActions result = mockMvc.perform(post(targetUrl)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(body));

        // アサーションの実施
        result.andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
              .andExpect(jsonPath("$.errors.codes[0]").value("ERR220001"));
    }

    /**
     * 異常系、不正のミックス
     * 007
     */
    @Test
    public void test007() throws Exception {
    	String testMethodName = new Throwable().getStackTrace()[0].getMethodName();
        String body = super.readForObject(testMethodName + ".json");

        // POSTリクエストの実施
        ResultActions result = mockMvc.perform(post(targetUrl)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(body));

        // アサーションの実施
        result.andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
              .andExpect(jsonPath("$.errors.codes[0]").value("ERR220002"))
              .andExpect(jsonPath("$.errors.codes[1]").value("ERR230001"))
              .andExpect(jsonPath("$.errors.codes[2]").value("ERR240001"));
    }

    /**
     * 異常系、システムエラー（DB接続できない）
     * 008
     */
    @Test
    public void test008() throws Exception {
    	String testMethodName = new Throwable().getStackTrace()[0].getMethodName();
        String body = super.readForObject(testMethodName + ".json");

        // POSTリクエストの実施
        ResultActions result = mockMvc.perform(post(targetUrl)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(body));

        // アサーションの実施
        result.andExpect(status().is(HttpStatus.INTERNAL_SERVER_ERROR.value()))
              .andExpect(jsonPath("$.errors.codes[0]").value("ERR999999"));
    }

}
