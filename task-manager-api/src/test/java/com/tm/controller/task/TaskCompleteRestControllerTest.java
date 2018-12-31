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
import com.tm.dao.repository.TaskHistoryRepository;
import com.tm.dto.TaskHistory;
import com.tm.dto.TaskHistoryExample;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("unit")
public class TaskCompleteRestControllerTest extends BaseControllerTestUtil{

    @Autowired
    TaskHistoryRepository taskHistoryRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private String targetUrl = "http://localhost:18080/api/v1/task/complete";

    @Before
    public void setupMock() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /**
     * 正常系、有効なタスクを完了する
     * 001
     * @throws Exception
     */
    @Test
    @Sql({"classpath:/com/tm/controller/framework/reset_db.sql", "classpath:/com/tm/controller/framework/task_common_data.sql"})
    public void test001() throws Exception {
        String testMethodName = new Throwable().getStackTrace()[0].getMethodName();
        String body = super.readForObject(testMethodName + ".json");

        // POSTリクエストの実施
        ResultActions result = mockMvc.perform(patch(targetUrl)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(body));

        TaskHistoryExample ex = new TaskHistoryExample();
        ex.createCriteria().andCompletedFlagEqualTo("1");
        List<TaskHistory> target = taskHistoryRepository.selectByExample(ex);

        // 結果の検証
        assertThat(target.size(), is(1));
        result.andExpect(status().is(HttpStatus.OK.value()))
              .andExpect(jsonPath("$.taskId").value("180101000a"))
              .andExpect(jsonPath("$.errors").doesNotExist());
    }

    /**
     * 異常系、10桁でないタスクIDを弾く
     * 002
     * @throws Exception
     */
    @Test
    public void test002() throws Exception {
        String testMethodName = new Throwable().getStackTrace()[0].getMethodName();
        String body = super.readForObject(testMethodName + ".json");

        // POSTリクエストの実施
        ResultActions result = mockMvc.perform(patch(targetUrl)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(body));

        // 結果の検証
        result.andExpect(status().is(HttpStatus.OK.value()))
              .andExpect(jsonPath("$.errors.codes[0]").value("ERR210003"))
              .andExpect(jsonPath("$.taskId").doesNotExist());
    }

    /**
     * 異常系、空のタスクIDを弾く
     * 003
     * @throws Exception
     */
    @Test
    public void test003() throws Exception {
        String testMethodName = new Throwable().getStackTrace()[0].getMethodName();
        String body = super.readForObject(testMethodName + ".json");

        // POSTリクエストの実施
        ResultActions result = mockMvc.perform(patch(targetUrl)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(body));

        // 結果の検証
        result.andExpect(status().is(HttpStatus.OK.value()))
              .andExpect(jsonPath("$.errors.codes[0]").value("ERR210001"))
              .andExpect(jsonPath("$.taskId").doesNotExist());
    }

    /**
     * 異常系、システムエラー（ここではDB接続できないケース）
     * 004
     * @throws Exception
     */
    @Test
    public void test004() throws Exception {
        String testMethodName = new Throwable().getStackTrace()[0].getMethodName();
        String body = super.readForObject(testMethodName + ".json");

        // POSTリクエストの実施
        ResultActions result = mockMvc.perform(patch(targetUrl)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(body));

        // 結果の検証
        result.andExpect(status().is(HttpStatus.INTERNAL_SERVER_ERROR.value()))
            .andExpect(jsonPath("$.errors.codes[0]").value("ERR999999"))
            .andExpect(jsonPath("$.taskId").doesNotExist());
    }

}
