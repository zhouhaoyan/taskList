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

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class UserTestCase {

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

    /**
     * 用户注册单元测试
     *
     * @throws Exception
     */
    @Test
    public void singInTest() throws Exception {

        mockMvc.perform(get("/singin")
                .param("account", "test").param("password","123456")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(0)))
                .andExpect(jsonPath("$.message", is("当前账户已经被使用")));
    }


    @Test
    public void singUpTest() throws Exception {
        mockMvc.perform(get("/singup")
                .param("account", "test").param("password","123456")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(1)))
                .andExpect(jsonPath("$.message", is("success")));

    }



}
