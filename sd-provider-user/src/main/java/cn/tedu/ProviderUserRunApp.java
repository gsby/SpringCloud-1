package cn.tedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author fhy
 * time:2019/7/11
 * Eureka�Ŀͻ��ˣ�����������Ҫ����Ҫɨ��İ�����һ�㣬����cn.tedu�������Ӱ��ĸ���
 */
@EnableEurekaClient
@SpringBootApplication
public class ProviderUserRunApp {
	
	public static void main(String[] args) {
		SpringApplication.run(ProviderUserRunApp.class, args);
	}
}