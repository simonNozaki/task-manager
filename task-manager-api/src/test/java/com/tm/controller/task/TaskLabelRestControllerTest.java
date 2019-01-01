package com.tm.controller.task;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

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
import com.tm.dao.repository.TaskLabelRepository;
import com.tm.dto.TaskLabel;
import com.tm.dto.TaskLabelExample;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("unit")
public class TaskLabelRestControllerTest extends BaseControllerTestUtil{

    @Autowired
    TaskLabelRepository taskLabelRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private String targetUrl = "http://localhost:18080/api/v1/task/label";


    /**
     * モックの原型をインジェクションして、データソースを初期化
     */
    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /**
     * 正常系、有効なラベルを登録する
     * 001
     * @throws Exception
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

        TaskLabelExample ex = new TaskLabelExample();
        ex.createCriteria().andTaskLabelEqualTo("sample label");
        List<TaskLabel> target = taskLabelRepository.selectByExample(ex);

        // 結果の検証
        assertThat(target.size(), is(1));
        result.andExpect(status().is(HttpStatus.CREATED.value()))
              .andExpect(jsonPath("$.taskLabel").value("sample label"));
    }

    /**
     * 異常系、規定の長さ以上の文字列を含むラベル情報を弾く
     * 002
     * @throws Exception
     */
    @Test
    public void test002() throws Exception {
        String testMethodName = new Throwable().getStackTrace()[0].getMethodName();
        String body = super.readForObject(testMethodName + ".json");

        // POSTリクエストの実施
        ResultActions result = mockMvc.perform(post(targetUrl)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(body));

        // 結果の検証
        result.andExpect(status().is(HttpStatus.CREATED.value()))
              .andExpect(jsonPath("$.errors.codes[0]").value("ERR230001"));
    }

    /**
     * 異常系、9桁の利用者IDのラベル情報を弾く
     * 003
     * @throws Exception
     */
    @Test
    public void test003() throws Exception {
        String testMethodName = new Throwable().getStackTrace()[0].getMethodName();
        String body = super.readForObject(testMethodName + ".json");

        // POSTリクエストの実施
        ResultActions result = mockMvc.perform(post(targetUrl)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(body));

        // 結果の検証
        result.andExpect(status().is(HttpStatus.CREATED.value()))
              .andExpect(jsonPath("$.errors.codes[0]").value("ERR110003"));
    }

    /**
     * 異常系、空のラベル情報を弾く
     * 004
     * @throws Exception
     */
    @Test
    public void test004() throws Exception {
        String testMethodName = new Throwable().getStackTrace()[0].getMethodName();
        String body = super.readForObject(testMethodName + ".json");

        // POSTリクエストの実施
        ResultActions result = mockMvc.perform(post(targetUrl)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(body));

        // 結果の検証
        result.andExpect(status().is(HttpStatus.CREATED.value()))
              .andExpect(jsonPath("$.errors.codes[0]").value("ERR910001"));
    }

    /**
     * 異常系、利用者IDとラベル名の不正ミックス
     * 005
     * @throws Exception
     */
    @Test
    public void test005() throws Exception {
        String testMethodName = new Throwable().getStackTrace()[0].getMethodName();
        String body = super.readForObject(testMethodName + ".json");

        // POSTリクエストの実施
        ResultActions result = mockMvc.perform(post(targetUrl)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(body));

        // 結果の検証
        result.andExpect(status().is(HttpStatus.CREATED.value()))
              .andExpect(jsonPath("$.errors.codes[0]").value("ERR110003"))
              .andExpect(jsonPath("$.errors.codes[1]").value("ERR230001"));
    }

    /**
     * 異常系、システムエラー
     * 006
     * @throws Exception
     */
    @Test
    public void test006() throws Exception {
        String testMethodName = new Throwable().getStackTrace()[0].getMethodName();
        String body = super.readForObject(testMethodName + ".json");

        // POSTリクエストの実施
        ResultActions result = mockMvc.perform(post(targetUrl)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(body));

        // 結果の検証
        result.andExpect(status().is(HttpStatus.INTERNAL_SERVER_ERROR.value()))
            .andExpect(jsonPath("$.errors.codes[0]").value("ERR999999"))
            .andExpect(jsonPath("$.labels").doesNotExist());
    }

}
