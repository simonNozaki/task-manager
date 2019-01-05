package com.tm.controller.user;

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
import com.tm.dao.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("unit")
public class UserSigninRestControllerTest extends BaseControllerTestUtil {

	@Autowired
    UserRepository userRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private String targetUrl = "http://localhost:18080/api/v1/user/signin";

    /**
     * モックの原型をインジェクションして、データソースを初期化
     */
    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /**
     * 正常系、有効な利用者情報で認証できることを確認する
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

        // アサーションの実施
        result.andExpect(status().is(HttpStatus.OK.value()))
              .andExpect(jsonPath("$.userId").value("TM00000001"))
              .andExpect(jsonPath("$.userName").value("test user"))
              .andExpect(jsonPath("$.email").value("test@example.com"))
              .andExpect(jsonPath("$.errors").doesNotExist());
    }

    /**
     * 異常系、規定の長さ以上のメールアドレスを弾く
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
              .andExpect(jsonPath("$.errors.codes[0]").value("ERR130002"));
    }

    /**
     * 異常系、規定の長さ以上のパスワードを弾く
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
              .andExpect(jsonPath("$.errors.codes[0]").value("ERR140002"));
    }

    /**
     * 異常系、入力不正のミックス
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
              .andExpect(jsonPath("$.errors.codes[0]").value("ERR130002"))
              .andExpect(jsonPath("$.errors.codes[1]").value("ERR140002"));
    }

    /**
     * 異常系、対象利用者不在
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
              .andExpect(jsonPath("$.errors.codes[0]").value("ERR160001"));
    }

    /**
     * 異常系、システムエラー（ここではDB接続できないケース）
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

        // 結果の検証
        result.andExpect(status().is(HttpStatus.INTERNAL_SERVER_ERROR.value()))
            .andExpect(jsonPath("$.errors.codes[0]").value("ERR999999"))
            .andExpect(jsonPath("$.userName").doesNotExist());
    }

}
