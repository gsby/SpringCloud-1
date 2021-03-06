package cn.tedu.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("provider-user")
public interface HelloFeign {
	//必须和调用服务返回值、方法名、参数、参数类型、参数注解一模一样
	@RequestMapping("/hello/{name}")
	public String hello(@PathVariable(value="name") String name);
}
