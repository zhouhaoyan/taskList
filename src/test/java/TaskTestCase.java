import jdk.nashorn.internal.parser.Token;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import person.zhy.App;
import person.zhy.utils.jwt.Jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class TaskTestCase {

    @Autowired
    private WebApplicationContext context;

    //模拟mvc对象类.
    private MockMvc mockMvc;



    @Before
    public void setup() {
       /*
        * MockMvcBuilders使用构建MockMvc对象.
        */
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void taskCreateTest() throws Exception {

        mockMvc.perform(post("/login/task")
                .param("token",this.genToken()).param("des","明天早上8点起来吃早饭!")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(1)))
                .andExpect(jsonPath("$.message", is("success")));
    }

    @Test
    public void taskUpdateTest() throws Exception {

        mockMvc.perform(put("/login/task").param("id","1")
                .param("token",this.genToken())
                        .param("des","明天早上9点起来吃早饭!")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(1)))
                .andExpect(jsonPath("$.message", is("success")));
    }
    @Test
    public void taskFinishTest() throws Exception {

        mockMvc.perform(delete("/login/task")
                .param("token",this.genToken()).param("id","1")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(1)))
                .andExpect(jsonPath("$.message", is("success")));
    }
    @Test
    public void taskListTest() throws Exception {

        mockMvc.perform(get("/login/task")
                .param("token",this.genToken())
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(1)))
                .andExpect(jsonPath("$.data", notNullValue()))
                .andExpect(jsonPath("$.message", is("success")));
    }

    public String genToken(){
        // 正常生成token----------------------------------------------------------------------------------------------------
        String token = null;
        Map<String, Object> payload = new HashMap<String, Object>();
        Date date = new Date();
        payload.put("uid", "1");// 用户id
        payload.put("iat", date.getTime());// 生成时间:当前
        payload.put("ext", date.getTime() + 2000 * 60 * 60);// 过期时间2小时
        token = Jwt.createToken(payload);
        System.out.println("新生成的token是：" + token+"\n马上将该token进行校验");
        return  token;
    }


}
