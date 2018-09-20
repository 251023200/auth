package com.sky.auth.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sky.auth.domain.Session;
import com.sky.auth.dto.Response;

@Deprecated
@Controller
public class AuthorizationController {
	
	//@Autowired
	private RedisTemplate<String, Session> redisTemplate;
	
	/**
	 * 验证权限
	 * @param accessToken 访问凭证
	 * @param url 访问地址
	 * @return
	 */
	@RequestMapping(value="/authorization",method=RequestMethod.POST)
	@ResponseBody
	public Response verify(@RequestParam String accessToken,@RequestParam String url) {
		Response response = new Response();
		boolean isvalid = verifyFromCache(accessToken,url);	//先从缓存验证
		if(isvalid){
			response.setCode("0");
		}else{
			response.setCode("1");
		}
		return response;
	}
	
	/**
	 * 从缓存中校验
	 * @param token
	 * @param url
	 * @return
	 */
	private boolean verifyFromCache(String token,String url){
		Session session = redisTemplate.opsForValue().get(token);
		if(session==null){
			return false;
		}
		String permissionsStr = session.getPermissions();
		String[] permissions = permissionsStr.split(",");
		for(int i=0;i<permissions.length;i++){
			if(permissions[i].equals(url)){
				return true;
			}
		}
		return false;
	}
	
}
