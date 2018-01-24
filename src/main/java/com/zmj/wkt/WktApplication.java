package com.zmj.wkt;

import com.zmj.wkt.config.CorsFilter;
import com.zmj.wkt.utils.socket.Server;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
@Configuration
@EnableDiscoveryClient
@EnableFeignClients
@ConfigurationProperties
@EnableAutoConfiguration
@MapperScan("com.zmj.wkt.mapper")
public class WktApplication {
	public static void main(String[] args) {
		SpringApplication.run(WktApplication.class, args);
		String show = "code is far away from bug with the animal protecting\n" +
				" *  ┏┓　　　┏┓\n" +
				" *┏┛┻━━━┛┻┓\n" +
				" *┃　　　　　　　┃\n" +
				" *┃　　　━　　　┃\n" +
				" *┃　┳┛　┗┳　┃\n" +
				" *┃　　　　　　　┃\n" +
				" *┃　　　┻　　　┃\n" +
				" *┃　　　　　　　┃\n" +
				" *┗━┓　　　┏━┛\n" +
				" *　　┃　　　┃神兽保佑\n" +
				" *　　┃　　　┃代码无BUG！\n" +
				" *　　┃　　　┗━━━┓\n" +
				" *　　┃　　　　　　　┣┓\n" +
				" *　　┃　　　　　　　┏┛\n" +
				" *　　┗┓┓┏━┳┓┏┛\n" +
				" *　　　┃┫┫　┃┫┫\n" +
				" *　　　┗┻┛　┗┻┛";
		String success = "---------------------启动成功！---------------------";
		System.out.println(show);
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		executorService.execute(new Runnable() {
			@Override
			public void run() {
				Server.start();
			}
		});
		System.out.println(success);
	}

	@Bean
	public FilterRegistrationBean indexFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean(new CorsFilter());
		registration.addUrlPatterns("/");
		return registration;
	}
}
