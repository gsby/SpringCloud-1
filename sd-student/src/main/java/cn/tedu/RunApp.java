package cn.tedu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//��ʶ��һ��springboot����Ŀ
@SpringBootApplication
//�ҵ����е�mapper�ӿ�
@MapperScan("cn.tedu.mapper")  
public class RunApp {
	public static void main(String[] args) {
		SpringApplication.run(RunApp.class, args);
	}
}