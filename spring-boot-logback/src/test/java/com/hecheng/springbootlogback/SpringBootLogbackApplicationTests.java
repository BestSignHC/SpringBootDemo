package com.hecheng.springbootlogback;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class SpringBootLogbackApplicationTests {

    @Test
    void contextLoads() {
        Logger logger = LoggerFactory.getLogger(SpringBootLogbackApplicationTests.class);

        Logger request = LoggerFactory.getLogger("request");
        Logger info = LoggerFactory.getLogger("info");
        Logger error = LoggerFactory.getLogger("error");
        info.info("info log");
        error.error("error log");
        request.info("request log");
        log.info("this is info log");
        logger.info("this is info log2");
    }

}
