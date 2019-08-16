package com.hc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 程序启动class，一般放在外层
 *
 * @PSpringBootApplication 等价于@Configuration, @EnableAutoConfiguration and @ComponentScan一起使用
 */


@SpringBootApplication
@EnableAutoConfiguration //一般放置在启动class上
public class SpringBootDemoApplication {

	public static void main(String[] args) {
		SpringApplication.setAddCommandLineProperties(false);   //设置不允许通过命令行改变配置项
//        java -jar xxx.jar --server.port=8888
		SpringApplication.run(SpringBootDemoApplication.class, args);
	}
}
