package com.sky.auth.oauth2.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sky.auth.exception.TokenExpireException;
import com.sky.auth.exception.TokenNotFountException;


/**
 * 
 * @author 杨帆
 *
 */
@Controller
public class ApiAuthorityController {
	
	int ACCESS_GRANTED = 1;
	int ACCESS_ABSTAIN = 0;
	int ACCESS_DENIED = -1;

//	@Autowired
//	private UserService userService;
	
	@Autowired
	private TokenStore tokenStore; 
	
	/**
	 * 校验用户(userId)是否有访问服务(serviceId)的接口(url)权限
	 * POST http://127.0.0.1:8085/check-authority?userId=d023be9ad7334ef1808b3f64c4381933
	 * {"serviceId":"auth",“method":"GET","url":"/applications/{id}"} 
	 * @param userId 用户编号
	 * @param serviceId 服务id
	 * @param method 访问方法
	 * @param url 接口
	 * @return
	 */
	@RequestMapping(path="/check-authority",method=RequestMethod.POST)
	@ResponseBody
	public int checkAuthority(@RequestParam String accessToken,@RequestBody Api api) throws
		InvalidTokenException {
		OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(accessToken);
		if (oAuth2AccessToken == null) {
			throw new TokenNotFountException("Invalid access token: " + accessToken);
		}
		else if (oAuth2AccessToken.isExpired()) {
			tokenStore.removeAccessToken(oAuth2AccessToken);
			throw new TokenExpireException("Access token expired: " + accessToken);
		}

		OAuth2Authentication authentication = tokenStore.readAuthentication(accessToken);
		if (authentication == null) {
			// in case of race condition
			throw new InvalidTokenException("Invalid access token: " + accessToken);
		}
		
		String method = api.getMethod();
		String url = api.getUrl();
		String serviceId = api.getServiceId();
		for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
			ApiAuthority apiAuthority = (ApiAuthority)grantedAuthority;
			String apiUrl = apiAuthority.getUrl();
			String apiMethod = apiAuthority.getMethod();
			String appCode = apiAuthority.getAppCode();
			if(!serviceId.equals(appCode)){
				continue;
			}
			if(!"ALL".equalsIgnoreCase(apiMethod) && !method.equalsIgnoreCase(apiMethod)){
				continue;
			}
			AntPathMatcher antPathMatcher = new AntPathMatcher();
			if(antPathMatcher.match(apiUrl, url)){//匹配成功
				return ACCESS_GRANTED;
			}	
		}
		
		return ACCESS_DENIED;
	}

	
	/**
	 * 校验用户(userId)是否有访问服务(serviceId)的接口(url)权限
	 * POST http://127.0.0.1:8085/check-authority?userId=d023be9ad7334ef1808b3f64c4381933
	 * {"serviceId":"auth",“method":"GET","url":"/applications/{id}"} 
	 * @param userId 用户编号
	 * @param serviceId 服务id
	 * @param method 访问方法
	 * @param url 接口
	 * @return
	 */
//	@RequestMapping(path="/check-authority-db",method=RequestMethod.POST)
//	@ResponseBody
//	public int checkAuthorityFromDB(@RequestParam String userId,@RequestBody Api api){
//		String url = api.getUrl();
//		if(url==null){
//			
//		}
//		String method = api.getMethod();
//		if(method==null){
//			
//		}
//		String serviceId = api.getServiceId();
//		if(serviceId==null){
//			
//		}
//		List<Operation> operations = userService.getAllOperations(userId);
//		for(int i=0;i<operations.size();i++){
//			Operation operation = operations.get(i);
//			String apiMethod = operation.getMethod();
//			String apiUrl = operation.getUrl();
//			int index = apiUrl.indexOf("?");
//			if(index>0)apiUrl=apiUrl.substring(0, index);
//			index = url.indexOf("?");
//			if(index>0)url=url.substring(0, index);
//			//String appName = operation.getAppName();
//			String appServiceId = operation.getAppCode();
//			if(serviceId.equals(appServiceId) && ("ALL".equalsIgnoreCase(apiMethod) || method.equalsIgnoreCase(apiMethod)) ){
//				AntPathMatcher antPathMatcher = new AntPathMatcher();
//				if(antPathMatcher.match(apiUrl, url)){
//					return 1;
//				}
//			}
//		}
//		return 0;
//	}

}
