package com.tm.controller.task;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.tm.TaskManagerApiApplication;
import com.tm.controller.framework.BaseControllerTestUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=TaskManagerApiApplication.class)
@ActiveProfiles(profiles="unit")
public class TaskFetchRestControllerTest extends BaseControllerTestUtil {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private String targetUrl = "http://localhost:18080/api/v1/task/fetch?userId={userId}";

    @Before
    public void setupMock() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /**
     * 正常系、有効な利用者IDに紐づくタスクのリストを取得します。
     * 001
     * @throws Exception
     */
    @Test
    @Sql({"classpath:/com/tm/controller/framework/reset_db.sql", "classpath:/com/tm/controller/framework/task_common_data.sql"})
    public void test001() throws Exception {

        ResultActions result = mockMvc.perform(get(targetUrl, "TM00000001"));

        // 結果の検証
        result.andExpect(status().is(HttpStatus.ACCEPTED.value()))
              .andExpect(jsonPath("$.tasks[0].taskId").value("180101000a"))
              .andExpect(jsonPath("$.tasks[1].taskId").value("180101000b"))
              .andExpect(jsonPath("$.tasks[2].taskId").value("180101000c"))
              .andExpect(jsonPath("$.tasks[3].taskId").doesNotExist());
    }

    /**
     * 異常系、9桁の利用者IDを弾く
     * 002
     * @throws Exception
     */
    @Test
    public void test002() throws Exception {

        ResultActions result = mockMvc.perform(get(targetUrl, "TM0000001"));

        // 結果の検証
        result.andExpect(status().is(HttpStatus.ACCEPTED.value()))
              .andExpect(jsonPath("$.errors.codes[0]").value("ERR110003"))
              .andExpect(jsonPath("$.labels").doesNotExist());
    }

    /**
     * 異常系、利用者IDが空のリクエストを弾く
     * 003
     * @throws Exception
     */
    @Test
    public void test003() throws Exception {

        ResultActions result = mockMvc.perform(get(targetUrl, ""));

        // 結果の検証
        result.andExpect(status().is(HttpStatus.ACCEPTED.value()))
              .andExpect(jsonPath("$.errors.codes[0]").value("ERR110003"))
              .andExpect(jsonPath("$.errors.codes[1]").value("ERR910001"))
              .andExpect(jsonPath("$.labels").doesNotExist());
    }

    /**
     * 異常系、システムエラー（このケースではDB接続なし）
     * 004
     * @throws Exception
     */
    @Test
    @Ignore
    public void test004() throws Exception {

        ResultActions result = mockMvc.perform(get(targetUrl, "TM00000001"));

        // 結果の検証
        result.andExpect(status().is(HttpStatus.INTERNAL_SERVER_ERROR.value()))
              .andExpect(jsonPath("$.errors.codes[0]").value("ERR999999"))
              .andExpect(jsonPath("$.labels").doesNotExist());
    }

}
