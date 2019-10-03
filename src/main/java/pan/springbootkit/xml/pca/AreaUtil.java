package pan.springbootkit.xml.pca;

import org.apache.commons.lang3.StringUtils;
import pan.springbootkit.utils.http.PanHttpResult;
import pan.springbootkit.utils.http.PanHttpUtil;
import pan.springbootkit.utils.json.PanJSONUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 省市县镇村 5 级 数据获取 util
 * 		截止2018年10月31日
 *
 * Created by panzhangbao on 2019-10-03 16:01:53
 * Copyright © 2019 panzhangbao. All rights reserved.
 */
public class AreaUtil {
	private static String baseUrl = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/";

	/**
	 * main Method
	 */
	public static void main(String[] args) {
		/**
		 * 获取省列表
		 */
		for (int i = 0; i < 10; i ++) {
			System.out.println(PanJSONUtil.listToJson(getProvinceList()));
		}

		/**
		 * 获取市列表
		 */
		AreaDTO areaDTO = new AreaDTO();
//		areaDTO.setHref("37.html");
//		areaDTO.setName("山东省");
//		getCityList(areaDTO);

		/**
		 * 获取县列表
		 */
//		areaDTO.setHref("37/3717.html");
//		areaDTO.setName("菏泽市");
//		getCountyList(areaDTO);

		/**
		 * 获取镇列表
		 */
//		areaDTO.setHref("37/17/371702.html");
//		areaDTO.setName("牡丹区");
//		getTownList(areaDTO);

		/**
		 * 获取村列表
		 */
//		areaDTO.setHref("37/17/02/371702109.html");
//		areaDTO.setName("安兴镇");
//		getVillageList(areaDTO);
	}

	/**
	 * 获取乡村列表
	 *
	 * @param areaDTO
	 * @return java.util.List<pan.springbootkit.xml.pca.AreaDTO>
	 * @date 2019-10-03 23:59
	 * @author panzhangbao
	 */
	public static List<AreaDTO> getVillageList(AreaDTO areaDTO) {
		System.out.println("查询村");

		if (null == areaDTO || StringUtils.isBlank(areaDTO.getHref())) {
			return null;
		}

		PanHttpResult result = PanHttpUtil.doGet(baseUrl + areaDTO.getHref());
		if (!result.getCode().equals(200)) {
			return getVillageList(areaDTO);
		}
		String s = result.getData();

		/**
		 * IP 限制后，循环调用
		 */
		if (!s.contains("villagetr")) {
			return getVillageList(areaDTO);
		}

		// 去首
		s = s.substring(s.indexOf("<tr class='villagetr'>"));
		// 去尾
		s = s.substring(0, s.indexOf("</table>"));

		// 去 <tr class='villagetr'></tr>
		while (s.contains("villagetr")) {
			Integer i = s.indexOf("villagetr");
			String s1 = s.substring(0, i - 11);
			String s2 = s.substring(i + 11);
			s = s1 + s2;
		}
		while (s.contains("tr")) {
			Integer i = s.indexOf("tr");
			String s1 = s.substring(0, i - 2);
			String s2 = s.substring(i + 3);
			s = s1 + s2;
		}

		List<AreaDTO> villageList = new ArrayList<>();

		while (s.contains("td")) {
			s = s.substring(s.indexOf("</td>") + 5);
			s = s.substring(s.indexOf("</td>") + 5);

			AreaDTO a = new AreaDTO();
			a.setName(s.substring(4, s.indexOf("</td>")));
			villageList.add(a);

			if ((s.indexOf("</td>") + 5) < s.length()) {
				s = s.substring(s.indexOf("</td>") + 5);
			}else {
				s = "";
			}
		}

		return villageList;
	}

	/**
	 * 获取镇列表
	 *
	 * @param areaDTO
	 * @return java.util.List<pan.springbootkit.xml.pca.AreaDTO>
	 * @date 2019-10-03 23:45
	 * @author panzhangbao
	 */
	public static List<AreaDTO> getTownList(AreaDTO areaDTO) {
		System.out.println("查询镇");

		if (null == areaDTO || StringUtils.isBlank(areaDTO.getHref())) {
			return null;
		}

		PanHttpResult result = PanHttpUtil.doGet(baseUrl + areaDTO.getHref());
		if (!result.getCode().equals(200)) {
			return getTownList(areaDTO);
		}
		String s = result.getData();

		/**
		 * IP 限制后，循环调用
		 */
		if (!s.contains("towntr")) {
			return getTownList(areaDTO);
		}

		// 去首
		s = s.substring(s.indexOf("<tr class='towntr'>"));
		// 去尾
		s = s.substring(0, s.indexOf("</table>"));

		// 去 <tr class='towntr'></tr>
		while (s.contains("towntr")) {
			Integer i = s.indexOf("towntr");
			String s1 = s.substring(0, i - 11);
			String s2 = s.substring(i + 8);
			s = s1 + s2;
		}
		while (s.contains("tr")) {
			Integer i = s.indexOf("tr");
			String s1 = s.substring(0, i - 2);
			String s2 = s.substring(i + 3);
			s = s1 + s2;
		}

		List<AreaDTO> townList = new ArrayList<>();

		while (s.contains("href")) {
			s = s.substring(s.indexOf("</td>") + 5);
			Integer i = s.indexOf("href");

			AreaDTO a = new AreaDTO();
			a.setHref(areaDTO.getHref().substring(0, 7) + s.substring(i + 6, i + 23));
			a.setName(s.substring(i + 25, s.indexOf("</a>")));
			townList.add(a);

			if ((s.indexOf("</td>") + 5) < s.length()) {
				s = s.substring(s.indexOf("</td>") + 5);
			}else {
				s = "";
			}
		}

		return townList;
	}

	/**
	 * 获取县列表
	 *
	 * @param areaDTO
	 * @return java.util.List<pan.springbootkit.xml.pca.AreaDTO>
	 * @date 2019-10-03 23:24
	 * @author panzhangbao
	 */
	public static List<AreaDTO> getCountyList(AreaDTO areaDTO) {
		System.out.println("查询县");

		if (null == areaDTO || StringUtils.isBlank(areaDTO.getHref())) {
			return null;
		}

		PanHttpResult result = PanHttpUtil.doGet(baseUrl + areaDTO.getHref());
		if (!result.getCode().equals(200)) {
			return getCountyList(areaDTO);
		}
		String s = result.getData();

		/**
		 * IP 限制后，循环调用
		 */
		if (!s.contains("countytr")) {
			return getCountyList(areaDTO);
		}

		// 去首
		s = s.substring(s.indexOf("<tr class='countytr'>"));
		// 去尾
		s = s.substring(0, s.indexOf("</table>"));

		// 去 <tr class='countytr'></tr>
		while (s.contains("countytr")) {
			Integer i = s.indexOf("countytr");
			String s1 = s.substring(0, i - 11);
			String s2 = s.substring(i + 10);
			s = s1 + s2;
		}
		while (s.contains("tr")) {
			Integer i = s.indexOf("tr");
			String s1 = s.substring(0, i - 2);
			String s2 = s.substring(i + 3);
			s = s1 + s2;
		}

		List<AreaDTO> countyList = new ArrayList<>();

		/**
		 * 过滤市辖区
		 */
		if (s.contains("市辖区")) {
			s = s.substring(s.indexOf("</td>") + 5);

			if ((s.indexOf("</td>") + 5) < s.length()) {
				s = s.substring(s.indexOf("</td>") + 5);
			}else {
				s = "";
			}
		}

		/**
		 * 处理其他区
		 */
		while (s.contains("href")) {
			s = s.substring(s.indexOf("</td>") + 5);
			Integer i = s.indexOf("href");

			AreaDTO a = new AreaDTO();
			a.setHref(s.substring(i + 6, i + 20));
			a.setName(s.substring(i + 22, s.indexOf("</a>")));
			countyList.add(a);

			if ((s.indexOf("</td>") + 5) < s.length()) {
				s = s.substring(s.indexOf("</td>") + 5);
			}else {
				s = "";
			}
		}

		return countyList;
	}

	/**
	 * 获取城市列表
	 *
	 * @param areaDTO
	 * @return java.util.List<pan.springbootkit.xml.pca.AreaDTO>
	 * @date 2019-10-03 22:56
	 * @author panzhangbao
	 */
	public static List<AreaDTO> getCityList(AreaDTO areaDTO) {
		System.out.println("查询市");

		if (null == areaDTO || StringUtils.isBlank(areaDTO.getHref())) {
			return null;
		}

		PanHttpResult result = PanHttpUtil.doGet(baseUrl + areaDTO.getHref());
		if (!result.getCode().equals(200)) {
			return getCityList(areaDTO);
		}
		String s = result.getData();

		/**
		 * IP 限制后，循环调用
		 */
		if (!s.contains("citytr")) {
			return getCityList(areaDTO);
		}

		// 去首
		s = s.substring(s.indexOf("<tr class='citytr'>"));
		// 去尾
		s = s.substring(0, s.indexOf("</table>"));

		// 去 <tr class='citytr'></tr>
		while (s.contains("citytr")) {
			Integer i = s.indexOf("citytr");
			String s1 = s.substring(0, i - 11);
			String s2 = s.substring(i + 8);
			s = s1 + s2;
		}
		while (s.contains("tr")) {
			Integer i = s.indexOf("tr");
			String s1 = s.substring(0, i - 2);
			String s2 = s.substring(i + 3);
			s = s1 + s2;
		}

		List<AreaDTO> cityList = new ArrayList<>();

		/**
		 * 过滤市辖区
		 */
		if (s.contains("市辖区")) {
			s = s.substring(s.indexOf("</td>") + 5);

			if ((s.indexOf("</td>") + 5) < s.length()) {
				s = s.substring(s.indexOf("</td>") + 5);
			}else {
				s = "";
			}
		}

		while (s.contains("href")) {
			s = s.substring(s.indexOf("</td>") + 5);
			Integer i = s.indexOf("href");

			AreaDTO c = new AreaDTO();
			c.setHref(s.substring(i + 6, i + 18));
			c.setName(s.substring(i + 20, s.indexOf("</a>")));
			cityList.add(c);

			if ((s.indexOf("</td>") + 5) < s.length()) {
				s = s.substring(s.indexOf("</td>") + 5);
			}else {
				s = "";
			}
		}

		return cityList;
	}

	/**
	 * 获取省数据列表
	 *
	 * @param
	 * @return java.util.List<pan.springbootkit.xml.pca.AreaDTO>
	 * @date 2019-10-03 22:26
	 * @author panzhangbao
	 */
	public static List<AreaDTO> getProvinceList() {
		System.out.println("查询省");

		PanHttpResult result = PanHttpUtil.doGet(baseUrl + "index.html");
		if (!result.getCode().equals(200)) {
			return getProvinceList();
		}
		String s = result.getData();

		/**
		 * IP 限制后，循环调用
		 */
		if (!s.contains("provincetr")) {
			return getProvinceList();
		}

		// 去首
		s = s.substring(s.indexOf("<tr class='provincetr'>"));
		// 去尾
		s = s.substring(0, s.indexOf("</table>"));

		// 去 <tr class='provincetr'></tr>
		while (s.contains("provincetr")) {
			Integer i = s.indexOf("provincetr");
			String s1 = s.substring(0, i - 11);
			String s2 = s.substring(i + 12);
			s = s1 + s2;
		}
		while (s.contains("tr")) {
			Integer i = s.indexOf("tr");
			String s1 = s.substring(0, i - 2);
			String s2 = s.substring(i + 3);
			s = s1 + s2;
		}

		List<AreaDTO> provinceList = new ArrayList<>();
		while (s.contains("href")) {
			Integer i = s.indexOf("href");

			AreaDTO a = new AreaDTO();
			a.setHref(s.substring(i + 6, i + 13));
			a.setName(s.substring(i + 15, s.indexOf("<br/")));
			provinceList.add(a);

			if ((s.indexOf("</td>") + 5) < s.length()) {
				s = s.substring(s.indexOf("</td>") + 5);
			}else {
				s = "";
			}
		}

		return provinceList;
	}
}
