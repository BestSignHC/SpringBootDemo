package com.hecheng.springbootlog4j2;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootLog4j2ApplicationTests {

    @Test
    void contextLoads() {
        Logger info = LoggerFactory.getLogger("info");
        Logger error = LoggerFactory.getLogger("error");
        Logger request = LoggerFactory.getLogger("request");

        info.info("info msg");
        error.error("error msg");
        request.info("request msg");
    }

}
