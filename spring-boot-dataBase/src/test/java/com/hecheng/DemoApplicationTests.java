package com.hecheng;

import com.alibaba.fastjson.JSON;
import com.hecheng.domain.UserAlias;
import com.hecheng.repository.UserAliasRepository;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private UserAliasRepository repository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testMongoFindAll() {
		List<UserAlias> all = repository.findAll();
		System.out.println(JSON.toJSONString(all));
	}

    @Test
    public void testMongo2() {
	    String mid = "E0000000000000000008";
	    String account = "1512632770609@qq.com";
        UserAlias byMidAndAccount = repository.findByMidAndAccount(mid, account);
        System.out.println(JSON.toJSONString(byMidAndAccount));
    }

	@Test
	public void testMongo() {
		UserAlias userAlias = new UserAlias();
		userAlias.setMid("111");
		userAlias.setAccount("test@qq,com");
		userAlias.setAlias("alias add by hc");
		userAlias.setCtime(System.currentTimeMillis());
		userAlias.setEtime(System.currentTimeMillis());

		userAlias = new UserAlias();
		userAlias.setMid("222");
		userAlias.setAccount("test2@qq,com");
		userAlias.setAlias("alias add by hc");
		userAlias.setCtime(System.currentTimeMillis());
		userAlias.setEtime(System.currentTimeMillis());

		Object save = repository.save(userAlias);
		System.out.println("save end:");
		System.out.println(JSON.toJSONString(save));

		List<UserAlias> getUserAlias = repository.findByAlias("alias add by hc");

		System.out.println("get end:");
		System.out.println(JSON.toJSONString(getUserAlias));
	}

}
