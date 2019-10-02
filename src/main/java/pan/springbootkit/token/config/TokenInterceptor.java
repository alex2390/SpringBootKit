package pan.springbootkit.token.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import pan.springbootkit.token.service.TokenService;
import pan.springbootkit.utils.http.PanHttpUtil;
import pan.springbootkit.utils.json.PanJSONUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * token 拦截器
 *
 * Created by panzhangbao on 2019-09-30 17:41:32
 * Copyright © 2019 panzhangbao. All rights reserved.
 */
@Slf4j
@Service
public class TokenInterceptor implements HandlerInterceptor {
	@Resource
	private TokenService tokenService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) {

		/**
		 * 校验 token
		 */
		if (checkToken(request)) {
			response.setStatus(200);
			response.setContentType("application/json;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			try {
				response.getWriter().write("{\"code\":2,\"message\":\"token 过期，请重新登录\",\"data\":null}");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}

		/**
		 * 校验权限
		 */

		/**
		 * 存储日志
		 */
		saveRequestData(request);

		// 只有返回 true 才会继续向下执行，返回false取消当前请求
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView){
	}

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e){
	}

	/**
	 * 校验 token
	 *
	 * @param request
	 * @return java.lang.Boolean
	 * @date 2019-10-02 10:21
	 * @author panzhangbao
	 */
	private Boolean checkToken(HttpServletRequest request) {
		// Bearer + token（中间有空格）
		String Authorization = request.getHeader("Authorization");

		if(StringUtils.isBlank(Authorization) || Authorization.length() <= 7) {
			return false;
		}

		String token = Authorization.substring(7);

		/**
		 * 对 token 进行验证等处理
		 */
		return tokenService.checkToken(token);
	}

	/**
	 * 记录下请求内容
	 *
	 * @param request
	 * @return void
	 * @date 2019-10-02 17:32
	 * @author panzhangbao
	 */
	private void saveRequestData(HttpServletRequest request) {
		Map requestMap = new HashMap(6);
		requestMap.put("url", request.getRequestURL().toString());
		requestMap.put("uri", request.getRequestURI());
		requestMap.put("method", request.getMethod());
		requestMap.put("header", PanHttpUtil.getHeadersInfo());
		Map paramsMap = PanHttpUtil.getAllRequestParam();
		if (CollectionUtils.isEmpty(paramsMap)) {
			requestMap.put("params", null);
		}else {
			requestMap.put("params", paramsMap);
		}

		requestMap.put("ip", PanHttpUtil.getIpAddress());

		log.info("\n" + PanJSONUtil.prettyJson(PanJSONUtil.mapToJson(requestMap)));
	}
}
