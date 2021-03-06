package cn.tedu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//标识是一个springboot的项目
@SpringBootApplication
//找到所有的mapper接口
@MapperScan("cn.tedu.mapper")  
public class RunApp {
	public static void main(String[] args) {
		SpringApplication.run(RunApp.class, args);
	}
}
