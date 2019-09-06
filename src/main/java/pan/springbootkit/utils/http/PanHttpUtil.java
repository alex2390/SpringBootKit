package pan.springbootkit.utils.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

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
	 * doGet
	 *
	 * @param httpUrl
	 * @return java.lang.String
	 * @date 2019-09-07 00:54
	 * @author panzhangbao
	 */
	public static PanHttpResult doGet(String httpUrl) {
		return doGet(httpUrl, null);
	}

	/**
	 * doGet
	 *
	 * @param httpUrl
	 * @param headerMap header 参数
	 * @return java.lang.String
	 * @date 2019-09-07 00:53
	 * @author panzhangbao
	 */
	public static PanHttpResult doGet(String httpUrl, Map<String, Object> headerMap) {
		HttpURLConnection connection = null;
		InputStream is = null;
		BufferedReader br = null;
		PanHttpResult result = new PanHttpResult();

		try {
			URL url = new URL(httpUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(RequestMethod.GET.name());
			connection.setConnectTimeout(CONNECT_TIMEOUT);
			connection.setReadTimeout(READ_TIMEOUT);
			if (!CollectionUtils.isEmpty(headerMap)) {
				for (Map.Entry<String, Object> entry : headerMap.entrySet()) {
					connection.setRequestProperty(entry.getKey(), entry.getValue().toString());
				}
			}
			connection.connect();

			if (connection.getResponseCode() == 200) {
				is = connection.getInputStream();
			}else {
				is = connection.getErrorStream();
			}

			br = new BufferedReader(new InputStreamReader(is, UTF_8));
			// 存放数据
			StringBuffer sbf = new StringBuffer();
			String temp;
			while ((temp = br.readLine()) != null) {
				sbf.append(temp);
				sbf.append("\r\n");
			}
			result.setData(sbf.toString());

		} catch (MalformedURLException e) {
			result.setCode(500);
			result.setData(e.toString());
			log.error("\nPanHttpUtil doGet MalformedURLException:\n", e.toString());
		} catch (IOException e) {
			result.setCode(500);
			result.setData(e.toString());
			log.error("\nPanHttpUtil doGet IOException:\n", e.toString());
		} finally {
			if (null != br) {
				try {
					br.close();
				} catch (IOException e) {
					result.setCode(500);
					result.setData(e.toString());
					e.printStackTrace();
				}
			}
			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
					result.setCode(500);
					result.setData(e.toString());
					e.printStackTrace();
				}
			}
			connection.disconnect();
		}
		try {
			result.setCode(connection.getResponseCode());
		} catch (IOException e) {
			result.setCode(500);
			result.setData(e.toString());
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 *
	 * @param httpUrl
	 *            发送请求的 URL
	 * @param paramJsonString
	 *            请求参数应该是{"key":"==g43sEvsUcbcunFv3mHkIzlHO4iiUIT R7WwXuSVKTK0yugJnZSlr6qNbxsL8OqCUAFyCDCoRKQ882m6cTTi0q9uCJsq JJvxS+8mZVRP/7lWfEVt8/N9mKplUA68SWJEPSXyz4MDeFam766KEyvqZ99d"}的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String doPost(String httpUrl, String paramJsonString) {
		HttpURLConnection connection = null;
		InputStream is = null;
		OutputStream os = null;
		BufferedReader br = null;
		String result = null;
		try {
			URL url = new URL(httpUrl);
			// 通过远程url连接对象打开连接
			connection = (HttpURLConnection) url.openConnection();
			// 设置连接请求方式
			connection.setRequestMethod("POST");
			// 设置连接主机服务器超时时间：15000毫秒
			connection.setConnectTimeout(15000);
			// 设置读取主机服务器返回数据超时时间：60000毫秒
			connection.setReadTimeout(60000);
			// 默认值为：false，当向远程服务器传送数据/写数据时，需要设置为true
			connection.setDoOutput(true);
			// 默认值为：true，当前向远程服务读取数据时，设置为true，该参数可有可无
			connection.setDoInput(true);
			// 设置传入参数的格式:请求参数应该是 name1=value1&name2=value2 的形式。
			connection.setRequestProperty("Content-Type", "application/json");
			// 设置鉴权信息：Authorization: Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0
			//connection.setRequestProperty("Authorization", "Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0");
			// 通过连接对象获取一个输出流
			os = connection.getOutputStream();
			// 通过输出流对象将参数写出去/传输出去,它是通过字节数组写出的
			os.write(paramJsonString.getBytes());
			// 通过连接对象获取一个输入流，向远程读取
			if (connection.getResponseCode() == 200) {
				is = connection.getInputStream();
				// 对输入流对象进行包装:charset根据工作项目组的要求来设置
				br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				StringBuffer sbf = new StringBuffer();
				String temp = null;
				// 循环遍历一行一行读取数据
				while ((temp = br.readLine()) != null) {
					sbf.append(temp);
					sbf.append("\r\n");
				}
				result = sbf.toString();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭资源
			if (null != br) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != os) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			// 断开与远程地址url的连接
			connection.disconnect();
		}
		return result;
	}

	/**
	 *
	 * @param httpUrl  请求的url
	 * @param param  form表单的参数（key,value形式）
	 * @return
	 */
	public static String doPostForm(String httpUrl, Map param) {

		HttpURLConnection connection = null;
		InputStream is = null;
		OutputStream os = null;
		BufferedReader br = null;
		String result = null;
		try {
			URL url = new URL(httpUrl);
			// 通过远程url连接对象打开连接
			connection = (HttpURLConnection) url.openConnection();
			// 设置连接请求方式
			connection.setRequestMethod("POST");
			// 设置连接主机服务器超时时间：15000毫秒
			connection.setConnectTimeout(15000);
			// 设置读取主机服务器返回数据超时时间：60000毫秒
			connection.setReadTimeout(60000);
			// 默认值为：false，当向远程服务器传送数据/写数据时，需要设置为true
			connection.setDoOutput(true);
			// 默认值为：true，当前向远程服务读取数据时，设置为true，该参数可有可无
			connection.setDoInput(true);
			// 设置传入参数的格式:请求参数应该是 name1=value1&name2=value2 的形式。
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			// 设置鉴权信息：Authorization: Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0
			//connection.setRequestProperty("Authorization", "Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0");
			// 通过连接对象获取一个输出流
			os = connection.getOutputStream();
			// 通过输出流对象将参数写出去/传输出去,它是通过字节数组写出的(form表单形式的参数实质也是key,value值的拼接，类似于get请求参数的拼接)
			os.write(createLinkString(param).getBytes());
			// 通过连接对象获取一个输入流，向远程读取
			if (connection.getResponseCode() == 200) {
				is = connection.getInputStream();
				// 对输入流对象进行包装:charset根据工作项目组的要求来设置
				br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				StringBuffer sbf = new StringBuffer();
				String temp = null;
				// 循环遍历一行一行读取数据
				while ((temp = br.readLine()) != null) {
					sbf.append(temp);
					sbf.append("\r\n");
				}
				result = sbf.toString();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭资源
			if (null != br) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != os) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			// 断开与远程地址url的连接
			connection.disconnect();
		}
		return result;
	}
	/**
	 * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
	 * @param params 需要排序并参与字符拼接的参数组
	 * @return 拼接后字符串
	 */
	public static String createLinkString(Map<String, String> params) {

		List<String> keys = new ArrayList<>(params.keySet());
		Collections.sort(keys);

		StringBuilder prestr = new StringBuilder();
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key);
			if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
				prestr.append(key).append("=").append(value);
			} else {
				prestr.append(key).append("=").append(value).append("&");
			}
		}

		return prestr.toString();
	}


	public static void main(String[] args) {
		String url = "https://ab.tcsmart.com.cn/api/lock/list";
		Map headerMap = new HashMap();
		headerMap.put("token", "ah^i2fo67a2jfiaf67gyfa87af8ag8f*&f");
		PanHttpResult result = PanHttpUtil.doGet(url, headerMap);
		System.out.println(result);

//		url = "http://localhost:8082/api/core/login";
//		JSONObject json = new JSONObject();
//		json.put("key", "==g43sEvsUcbcunFv3mHkIzlHO4iiUIT R7WwXuSVKTK0yugJnZSlr6qNbxsL8OqCUAFyCDCoRKQ882m6cTTi0q9uCJsq JJvxS+8mZVRP/7lWfEVt8/N9mKplUA68SWJEPSXyz4MDeFam766KEyvqZ99d");
//		String postResult = PanHttpUtil.doPost(url, json.toJSONString());
//		System.out.println(postResult);
//
//		url = "http://localhost:8082/api/test/testSendForm";
//		Map<String,String> map = new HashMap<>();
//		map.put("name", "测试表单请求");
//		String formResult = PanHttpUtil.doPostForm(url, map);
//		System.out.println(formResult);

	}
}
