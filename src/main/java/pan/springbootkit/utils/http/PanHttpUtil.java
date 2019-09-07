package pan.springbootkit.utils.http;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Http util
 *
 * Created by panzhangbao on 2019-09-07 00:16:53
 * Copyright © 2019 panzhangbao. All rights reserved.
 */
@Slf4j
public class PanHttpUtil {

	/**
	 * 连接超时时间 15 秒
	 */
	private static final Integer CONNECT_TIMEOUT = 15000;
	/**
	 * 读取超时时间 60 秒
	 */
	private static final Integer READ_TIMEOUT = 60000;

	/**
	 * UTF-8
	 */
	private static final String UTF_8 = "UTF-8";

	/**
	 * 内容类型
	 */
	private static final String CONTENT_TYPE = "Content-Type";

	/**
	 * 内容类型 json
	 */
	private static final String  CONTENT_TYPE_APPLICATION_JSON = "application/json";

	private static final String CONTENT_TYPE_FORM = "application/x-www-form-urlencoded";

	/**
	 * doGet
	 *
	 * @param httpUrl url
	 * @return java.lang.String
	 * @date 2019-09-07 00:54
	 * @author panzhangbao
	 */
	public static PanHttpResult doGet(String httpUrl) {
		/**
		 * 参数合法性校验
		 */
		if (StringUtils.isBlank(httpUrl)) {
			return PanHttpResult.SYSTEM_ERROR();
		}

		return doGet(httpUrl, null);
	}

	/**
	 * doGet
	 *
	 * @param httpUrl url
	 * @param headerMap header 参数
	 * @return java.lang.String
	 * @date 2019-09-07 00:53
	 * @author panzhangbao
	 */
	public static PanHttpResult doGet(String httpUrl, Map<String, Object> headerMap) {
		/**
		 * 参数合法性校验
		 */
		if (StringUtils.isBlank(httpUrl)) {
			return PanHttpResult.SYSTEM_ERROR();
		}

		return defaultInition(httpUrl, RequestMethod.GET.name(), headerMap, null, null);
	}

	/**
	 * doPost
	 *
	 * @param httpUrl url
	 * @return pan.springbootkit.utils.http.PanHttpResult
	 * @date 2019-09-07 23:21
	 * @author panzhangbao
	 */
	public static PanHttpResult doPost(String httpUrl) {
		/**
		 * 参数合法性校验
		 */
		if (StringUtils.isBlank(httpUrl)) {
			return PanHttpResult.SYSTEM_ERROR();
		}

		return doPost(httpUrl, null);
	}

	/**
	 * doPost
	 *
	 * @param httpUrl url
	 * @param headerMap header 参数
	 * @return pan.springbootkit.utils.http.PanHttpResult
	 * @date 2019-09-08 00:14
	 * @author panzhangbao
	 */
	public static PanHttpResult doPost(String httpUrl, Map<String, Object> headerMap) {
		/**
		 * 参数合法性校验
		 */
		if (StringUtils.isBlank(httpUrl)) {
			return PanHttpResult.SYSTEM_ERROR();
		}

		return doPostByJson(httpUrl, headerMap, null);
	}

	/**
	 * doPostByJson
	 *
	 * @param httpUrl url
	 * @param paramsJsonString json 请求体
	 * @return pan.springbootkit.utils.http.PanHttpResult
	 * @date 2019-09-08 00:15
	 * @author panzhangbao
	 */
	public static PanHttpResult doPostByJson(String httpUrl, String paramsJsonString) {
		/**
		 * 参数合法性校验
		 */
		if (StringUtils.isBlank(httpUrl)) {
			return PanHttpResult.SYSTEM_ERROR();
		}

		return doPostByJson(httpUrl, null, paramsJsonString);
	}

	/**
	 * doPostByJson
	 *
	 * @param httpUrl url
	 * @param headerMap header 参数
	 * @param paramsJsonString json 请求体
	 * @return pan.springbootkit.utils.http.PanHttpResult
	 * @date 2019-09-08 00:15
	 * @author panzhangbao
	 */
	public static PanHttpResult doPostByJson(String httpUrl, Map<String, Object> headerMap, String paramsJsonString) {
		/**
		 * 参数合法性校验
		 */
		if (StringUtils.isBlank(httpUrl)) {
			return PanHttpResult.SYSTEM_ERROR();
		}

		return defaultInition(httpUrl, RequestMethod.POST.name(), headerMap, paramsJsonString, null);
	}

	/**
	 * doPostByForm
	 *
	 * @param httpUrl url
	 * @param formParamsMap form 表单请求体
	 * @return pan.springbootkit.utils.http.PanHttpResult
	 * @date 2019-09-08 00:16
	 * @author panzhangbao
	 */
	public static PanHttpResult doPostByForm(String httpUrl, Map<String, Object> formParamsMap) {
		/**
		 * 参数合法性校验
		 */
		if (StringUtils.isBlank(httpUrl)) {
			return PanHttpResult.SYSTEM_ERROR();
		}

		return doPostByForm(httpUrl, null, formParamsMap);
	}

	/**
	 * doPostByForm
	 *
	 * @param httpUrl url
	 * @param headerMap header 参数
	 * @param formParamsMap form 表单请求体
	 * @return pan.springbootkit.utils.http.PanHttpResult
	 * @date 2019-09-08 00:16
	 * @author panzhangbao
	 */
	public static PanHttpResult doPostByForm(String httpUrl, Map<String, Object> headerMap, Map<String, Object> formParamsMap) {
		/**
		 * 参数合法性校验
		 */
		if (StringUtils.isBlank(httpUrl)) {
			return PanHttpResult.SYSTEM_ERROR();
		}

		return defaultInition(httpUrl, RequestMethod.POST.name(), headerMap, null, formParamsMap);
	}

	/**
	 * doPut
	 *
	 * @param httpUrl url
	 * @return pan.springbootkit.utils.http.PanHttpResult
	 * @date 2019-09-08 00:17
	 * @author panzhangbao
	 */
	public static PanHttpResult doPut(String httpUrl) {
		/**
		 * 参数合法性校验
		 */
		if (StringUtils.isBlank(httpUrl)) {
			return PanHttpResult.SYSTEM_ERROR();
		}

		return doPut(httpUrl, null);
	}

	/**
	 * doPut
	 *
	 * @param httpUrl url
	 * @param headerMap header 参数
	 * @return pan.springbootkit.utils.http.PanHttpResult
	 * @date 2019-09-08 00:17
	 * @author panzhangbao
	 */
	public static PanHttpResult doPut(String httpUrl, Map<String, Object> headerMap) {
		/**
		 * 参数合法性校验
		 */
		if (StringUtils.isBlank(httpUrl)) {
			return PanHttpResult.SYSTEM_ERROR();
		}

		return doPutByJson(httpUrl, headerMap, null);
	}

	/**
	 * doPutByJson
	 *
	 * @param httpUrl url
	 * @param paramsJsonString json 请求体
	 * @return pan.springbootkit.utils.http.PanHttpResult
	 * @date 2019-09-08 00:17
	 * @author panzhangbao
	 */
	public static PanHttpResult doPutByJson(String httpUrl, String paramsJsonString) {
		/**
		 * 参数合法性校验
		 */
		if (StringUtils.isBlank(httpUrl)) {
			return PanHttpResult.SYSTEM_ERROR();
		}

		return doPutByJson(httpUrl, null, paramsJsonString);
	}

	/**
	 * doPutByJson
	 *
	 * @param httpUrl url
	 * @param headerMap header 参数
	 * @param paramsJsonString json 请求体
	 * @return pan.springbootkit.utils.http.PanHttpResult
	 * @date 2019-09-08 00:17
	 * @author panzhangbao
	 */
	public static PanHttpResult doPutByJson(String httpUrl, Map<String, Object> headerMap, String paramsJsonString) {
		/**
		 * 参数合法性校验
		 */
		if (StringUtils.isBlank(httpUrl)) {
			return PanHttpResult.SYSTEM_ERROR();
		}

		return defaultInition(httpUrl, RequestMethod.PUT.name(), headerMap, paramsJsonString, null);
	}

	/**
	 * doPutByForm
	 *
	 * @param httpUrl url
	 * @param formParamsMap form 表单请求体
	 * @return pan.springbootkit.utils.http.PanHttpResult
	 * @date 2019-09-08 00:17
	 * @author panzhangbao
	 */
	public static PanHttpResult doPutByForm(String httpUrl, Map<String, Object> formParamsMap) {
		/**
		 * 参数合法性校验
		 */
		if (StringUtils.isBlank(httpUrl)) {
			return PanHttpResult.SYSTEM_ERROR();
		}

		return doPutByForm(httpUrl, null, formParamsMap);
	}

	/**
	 * doPutByForm
	 *
	 * @param httpUrl url
	 * @param headerMap header 参数
	 * @param formParamsMap form 表单请求体
	 * @return pan.springbootkit.utils.http.PanHttpResult
	 * @date 2019-09-08 00:18
	 * @author panzhangbao
	 */
	public static PanHttpResult doPutByForm(String httpUrl, Map<String, Object> headerMap, Map<String, Object> formParamsMap) {
		/**
		 * 参数合法性校验
		 */
		if (StringUtils.isBlank(httpUrl)) {
			return PanHttpResult.SYSTEM_ERROR();
		}

		return defaultInition(httpUrl, RequestMethod.PUT.name(), headerMap, null, formParamsMap);
	}

	/**
	 * doDelete
	 *
	 * @param httpUrl url
	 * @return java.lang.String
	 * @date 2019-09-08 00:22
	 * @author panzhangbao
	 */
	public static PanHttpResult doDelete(String httpUrl) {
		/**
		 * 参数合法性校验
		 */
		if (StringUtils.isBlank(httpUrl)) {
			return PanHttpResult.SYSTEM_ERROR();
		}

		return doDelete(httpUrl, null);
	}

	/**
	 * doDelete
	 *
	 * @param httpUrl url
	 * @param headerMap header 参数
	 * @return java.lang.String
	 * @date 2019-09-08 00:22
	 * @author panzhangbao
	 */
	public static PanHttpResult doDelete(String httpUrl, Map<String, Object> headerMap) {
		/**
		 * 参数合法性校验
		 */
		if (StringUtils.isBlank(httpUrl)) {
			return PanHttpResult.SYSTEM_ERROR();
		}

		return defaultInition(httpUrl, RequestMethod.DELETE.name(), headerMap, null, null);
	}

	/**
	 * 默认初始化
	 *
	 * @param httpUrl url
	 * @param requestMethod 请求方法
	 * @param headerMap header 参数
	 * @param jsonParamsString json 请求体
 	 * @param formParamsMap  form 表单请求体
	 * @return pan.springbootkit.utils.http.PanHttpResult 返回结果
	 * @date 2019-09-08 00:03
	 * @author panzhangbao
	 */
	private static PanHttpResult defaultInition(String httpUrl,
										  String requestMethod,
										  Map<String, Object> headerMap,
										  String jsonParamsString,
										  Map<String, Object> formParamsMap) {
		/**
		 * 参数合法性校验
		 */
		if (StringUtils.isBlank(httpUrl) || StringUtils.isBlank(requestMethod)) {
			return PanHttpResult.SYSTEM_ERROR();
		}

		HttpURLConnection connection = null;
		InputStream is = null;
		OutputStream os = null;
		BufferedReader br = null;
		PanHttpResult result = new PanHttpResult();

		try {
			connection = (HttpURLConnection) new URL(httpUrl).openConnection();
			connection.setRequestMethod(requestMethod);
			connection.setConnectTimeout(CONNECT_TIMEOUT);
			connection.setReadTimeout(READ_TIMEOUT);

			if (RequestMethod.POST.name().equals(requestMethod) || RequestMethod.PUT.name().equals(requestMethod)) {
				// 默认值为：false，当向远程服务器传送数据/写数据时，需要设置为true
				connection.setDoOutput(true);
			}

			// header 参数
			if (!CollectionUtils.isEmpty(headerMap)) {
				headerMap.put(CONTENT_TYPE, CONTENT_TYPE_APPLICATION_JSON);
				for (Map.Entry<String, Object> entry : headerMap.entrySet()) {
					connection.setRequestProperty(entry.getKey(), entry.getValue().toString());
				}
			}

			if (StringUtils.isNotBlank(jsonParamsString)) {
				connection.setRequestProperty(CONTENT_TYPE, CONTENT_TYPE_APPLICATION_JSON);
			}
			if (!CollectionUtils.isEmpty(formParamsMap)) {
				connection.setRequestProperty(CONTENT_TYPE, CONTENT_TYPE_FORM);
			}

			if (RequestMethod.POST.name().equals(requestMethod) || RequestMethod.PUT.name().equals(requestMethod)) {
				// 通过连接对象获取一个输出流
				os = connection.getOutputStream();
				if (StringUtils.isNotBlank(jsonParamsString)) {
					// 通过输出流对象将参数写出去/传输出去,它是通过字节数组写出的
					os.write(jsonParamsString.getBytes());
				}

				if (!CollectionUtils.isEmpty(formParamsMap)) {
					// 通过输出流对象将参数写出去/传输出去,它是通过字节数组写出的(form表单形式的参数实质也是key,value值的拼接，类似于get请求参数的拼接)
					os.write(createLinkString(formParamsMap).getBytes());
				}
			}

			// 通过连接对象获取一个输入流，向远程读取
			if (connection.getResponseCode() == 200) {
				is = connection.getInputStream();
			}else {
				is = connection.getErrorStream();
			}

			// 对输入流对象进行包装:charset根据工作项目组的要求来设置
			br = new BufferedReader(new InputStreamReader(is, UTF_8));
			StringBuffer sbf = new StringBuffer();
			String temp;
			// 循环遍历一行一行读取数据
			while ((temp = br.readLine()) != null) {
				sbf.append(temp);
				sbf.append("\r\n");
			}
			result.setCode(connection.getResponseCode());
			result.setData(sbf.toString());
		} catch (MalformedURLException e) {
			result.setCode(500);
			result.setData(e.toString());
			log.error("\nPanHttpUtil " + requestMethod + " MalformedURLException:\n", e.toString());
		} catch (IOException e) {
			result.setCode(500);
			result.setData(e.toString());
			log.error("\nPanHttpUtil " + requestMethod + " IOException:\n", e.toString());
		} finally {
			// 关闭资源
			if (null != br) {
				try {
					br.close();
				} catch (IOException e) {
					result.setCode(500);
					result.setData(e.toString());
					log.error("\nPanHttpUtil " + requestMethod + " IOException:\n", e.toString());				}
			}
			if (null != os) {
				try {
					os.close();
				} catch (IOException e) {
					result.setCode(500);
					result.setData(e.toString());
					log.error("\nPanHttpUtil " + requestMethod + " IOException:\n", e.toString());				}
			}
			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
					result.setCode(500);
					result.setData(e.toString());
					log.error("\nPanHttpUtil " + requestMethod + " IOException:\n", e.toString());				}
			}
			// 断开与远程地址url的连接
			connection.disconnect();
		}

		return result;
	}

	/**
	 * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
	 *
	 * @param paramsMap 需要排序并参与字符拼接的参数组
	 * @return java.lang.String 拼接后字符串
	 * @date 2019-09-07 23:51
	 * @author panzhangbao
	 */
	private static String createLinkString(Map<String, Object> paramsMap) {
		/**
		 * 参数合法性校验
		 */
		if (CollectionUtils.isEmpty(paramsMap)) {
			return null;
		}

		List<String> keys = new ArrayList<>(paramsMap.keySet());
		Collections.sort(keys);

		StringBuilder result = new StringBuilder();
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = String.valueOf(paramsMap.get(key));

			// 拼接时，不包括最后一个&字符
			if (i == keys.size() - 1) {
				result.append(key).append("=").append(value);
			} else {
				result.append(key).append("=").append(value).append("&");
			}
		}

		return result.toString();
	}
}
