package com.hc;

import com.hc.domain.Author;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDemoApplicationTests {

    @Autowired
    Author author;

	@Test
	public void contextLoads() {
        System.out.println(author.getName());
        System.out.println(author.getMobile());
    }

    @Test
    public void testLog() {
        Logger errorfile = LoggerFactory.getLogger("error");
        errorfile.error("test");
    }

}
