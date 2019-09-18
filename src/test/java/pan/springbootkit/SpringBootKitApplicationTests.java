package pan.springbootkit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pan.springbootkit.constant.TestConstant;

import javax.annotation.Resource;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootKitApplicationTests {

	@Resource
	private MockMvc mockMvc;

	/**
	 * 查询用户列表测试
	 *
	 * @param
	 * @return void
	 * @date 2019-09-16 08:26
	 * @author panzhangbao
	 */
	@Test
	public void listUser() throws Exception {
		/**
		 * 打印请求参数
		 */

		/**
		 * 调接口
		 */
		String baseResult = mockMvc.perform(MockMvcRequestBuilders.get(TestConstant.BASE_URL + "api/db/db_user/user/list")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andReturn().getResponse().getContentAsString();

		/**
		 * 打印返回数据
		 */
		System.out.println("查询用户列表：\n" + baseResult);
	}

	@Test
	public void contextLoads() {
	}

}
