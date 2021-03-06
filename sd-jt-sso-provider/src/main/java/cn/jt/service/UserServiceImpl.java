package cn.jt.service;


import org.apache.commons.codec.cli.Digest;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.jt.common.service.RedisService;
import cn.jt.mapper.UserMapper;
import cn.jt.pojo.User;

@Service
public class UserServiceImpl implements UserService{
	private static final ObjectMapper MAPPER = new ObjectMapper();

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RedisService redisService;
	
	
	public Boolean check(String param, Integer typeVal) {
		String typeName = "";
		if(typeVal == 1)
			typeName = "username";
		else if(typeVal == 2)
			typeName = "phone";
		else if(typeVal == 3)
			typeName = "email";
		// TODO Auto-generated method stub
		int count = userMapper.check(param, typeName);
		return count>0 ? true : false;
	}
	
	public String query(String ticket){
		String userJson = redisService.get(ticket);
		return userJson;
	}

	@Override
	public String register(User user) {
		// 用户注册
		userMapper.insert(user);
		return user.getUsername();
	}

	@Override
	public String login(String username, String password) {
		// 登陆，通常习惯是只按用户名查
		User user = userMapper.login(username);
		String ticket = null;
		if(user != null){
			//进行密码比较
			password = DigestUtils.md5Hex(password);
			if(user.getPassword().equals(password)){
				//是系统用户，写入redis (K,V)
				//user对象转成userJson
				try {
					user.setPassword(null);//密码置空，不暴露密码
					String userJson = MAPPER.writeValueAsString(user);
					//三个要求：唯一性、动态性、混淆性
					ticket = DigestUtils.md5Hex("JT_TICKET"+System.currentTimeMillis()+ username);
					
					System.out.println(ticket);
					
					//存入redis,并设置过期时间
					redisService.set(ticket, userJson, 60*60*24*7); //不用算出结果，javac编译优化，自动计算
				} catch (JsonProcessingException e) {
					
					e.printStackTrace();
				}
			}
		}
		return ticket;
	}

	
}
