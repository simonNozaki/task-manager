package com.tm.controller.user;

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
import com.tm.dao.repository.UserRepository;
import com.tm.dto.Users;
import com.tm.dto.UsersExample;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("unit")
public class UserSignupRestControllerTest extends BaseControllerTestUtil {

	@Autowired
    UserRepository userRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private String targetUrl = "http://localhost:18080/api/v1/user/signup";


    /**
     * モックの原型をインジェクションして、データソースを初期化
     */
    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /**
     * 正常系、有効な利用者情報でデータが登録できることを確認する
     * @throws Exception
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

        UsersExample ex = new UsersExample();
        ex.createCriteria().andEmailEqualTo("ok@mock.com");
        List<Users> target = userRepository.selectByExample(ex);

        // アサーション
        assertThat(target.size(), is(1));

        result.andExpect(status().is(HttpStatus.CREATED.value()))
              .andExpect(jsonPath("$.userName").value("stubuser"));
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
        // アサーション
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
        // アサーション
        result.andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
              .andExpect(jsonPath("$.errors.codes[0]").value("ERR140002"));
    }

    /**
     * 異常系、規定の長さでない利用フラグを弾く
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
        // アサーション
        result.andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
              .andExpect(jsonPath("$.errors.codes[0]").value("ERR150003"));
    }

    /**
     * 異常系、定義されていないフラグを弾く
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
        // アサーション
        result.andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
              .andExpect(jsonPath("$.errors.codes[0]").value("ERR150004"));
    }

    /**
     * 異常系、規定の長さ以上の利用者名を弾く
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
        // アサーション
        result.andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
              .andExpect(jsonPath("$.errors.codes[0]").value("ERR120002"));
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
        // アサーション
        result.andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
              .andExpect(jsonPath("$.errors.codes[0]").value("ERR130002"))
              .andExpect(jsonPath("$.errors.codes[1]").value("ERR140002"))
              .andExpect(jsonPath("$.errors.codes[2]").value("ERR150003"));
    }

    /**
     * 異常系、重複して同じ利用者を登録できないこと
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
        // アサーション
        result.andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
              .andExpect(jsonPath("$.errors.codes[0]").value("ERR130004"));
    }

    /**
     * 異常系、システムエラー（ここではDB接続できないケース）
     * 009
     */
    @Test
    public void test009() throws Exception {
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
