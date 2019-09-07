package pan.springbootkit.utils.json;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * JSON util
 * 		本工具完全使用 fastjson 进行处理
 *
 * Created by panzhangbao on 2019-09-02 08:59:49
 * Copyright © 2019 panzhangbao. All rights reserved.
 */
@Slf4j
public class PanJSONUtil {

	/**
	 * json 转 Map
	 * 		多层嵌套时，null 属性会过滤掉，需要手动处理
	 *
	 * @param jsonStr
	 * @return java.util.Map
	 * @date 2019-09-02 09:02
	 * @author panzhangbao
	 */
	public static Map jsonToMap(String jsonStr) {
		/**
		 * 参数合法性校验
		 */
		if (StringUtils.isEmpty(jsonStr)) {
			return null;
		}

		Map resultMap = JSONObject.parseObject(jsonStr, LinkedHashMap.class, Feature.OrderedField);

		return resultMap;
	}

	/**
	 * json 转 实体类
	 *
	 * @param jsonStr
	 * @param cls
	 * @return T
	 * @date 2019-09-02 09:05
	 * @author panzhangbao
	 */
	public static <T> T jsonToEntity(String jsonStr, Class<T> cls) {
		/**
		 * 参数合法性校验
		 */
		if (StringUtils.isEmpty(jsonStr) || null == cls) {
			return null;
		}

		return JSONObject.parseObject(jsonStr, cls, Feature.OrderedField);
	}

	/**
	 * json 转 List	调用了 mapToEntity
	 *
	 * @param jsonStr
	 * @param cls
	 * @return java.util.List<T>
	 * @date 2019-09-02 09:06
	 * @author panzhangbao
	 */
	public static <T> List<T> jsonToList(String jsonStr, Class<T> cls) {

		if (StringUtils.isBlank(jsonStr) || null == cls) {
			return null;
		}
		List<T> resultList = new ArrayList<>();
		List<LinkedHashMap> list = JSONObject.parseArray(jsonStr, LinkedHashMap.class);
		list.forEach(e -> {
			resultList.add(mapToEntity(e, cls));
		});

		return resultList;
	}

	/**
	 * Map 装 json
	 *
	 * @param map
	 * @return java.lang.String
	 * @date 2019-09-02 09:13
	 * @author panzhangbao
	 */
	public static String mapToJson(Map map) {
		/**
		 * 参数合法性校验
		 */
		if (CollectionUtils.isEmpty(map)) {
			return null;
		}

		return JSONObject.toJSONString(map,
				SerializerFeature.WriteMapNullValue,
				SerializerFeature.WriteDateUseDateFormat);
	}

	/**
	 * Map 转实体类	调用了 mapToJson
	 *
	 * @param map
	 * @param cls
	 * @return T
	 * @date 2019-09-02 09:15
	 * @author panzhangbao
	 */
	public static <T> T mapToEntity(Map map, Class<T> cls) {
		/**
		 * 参数合法性校验
		 */
		if (CollectionUtils.isEmpty(map)) {
			return null;
		}

		return JSONObject.parseObject(mapToJson(map), cls);
	}

	/**
	 * 实体类转 json
	 * 		转换的时间为时间戳
	 *
	 * @param object
	 * @return
	 */
	public static String entityToJson(Object object) {
		/**
		 * 参数合法性校验
		 */
		if (null == object) {
			return null;
		}

		return JSONObject.toJSONString(object,
				SerializerFeature.WriteMapNullValue,
				SerializerFeature.WriteDateUseDateFormat);
	}

	/**
	 * 实体类转 Map	调用了 jsonToMap 和 entityToJson
	 * 		多层嵌套 null 属性会过滤掉，需要手动处理
	 *
	 * @param object
	 * @return java.util.Map
	 * @date 2019-09-02 11:17
	 * @author panzhangbao
	 */
	public static Map entityToMap(Object object){
		/**
		 * 参数合法性校验
		 */
		if (null == object) {
			return null;
		}

		String jsonStr = entityToJson(object);

		return jsonToMap(jsonStr);
	}

	/**
	 * List 转 JSON	调用了 entityToJson
	 *
	 * @param list
	 * @return java.lang.String
	 * @date 2019-09-02 11:22
	 * @author panzhangbao
	 */
	public static String listToJson(List list) {
		/**
		 * 参数合法性校验
		 */
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}

		List<String> jsonList = new ArrayList<>();
		list.forEach(e -> {
			jsonList.add(entityToJson(e));
		});

		return jsonList.toString();
	}

	/**
	 * main Method
	 */
//	public static void main(String[] args) {
//		TestModel testModel = new TestModel();
//		testModel.setCreatedTime(new Date());
//		testModel.setName("牛牛");
//		TestModel innerTestModel = new TestModel();
//		innerTestModel.setName("内部 model 名");
//		innerTestModel.setId(1);
//		testModel.setInnerModel(innerTestModel);
//		Map innerMap = new HashMap();
//		innerMap.put("money", 1111);
//		innerMap.put("size", null);
//		innerMap.put("date", new Date());
//		innerMap.put("name", "testInnerMap");
//		testModel.setInnerMap(innerMap);
//		List innerList = new ArrayList();
//		innerMap = new HashMap();
//		innerMap.put("money", 1);
//		innerMap.put("size", null);
//		innerMap.put("date", new Date());
//		innerMap.put("name", "testInnerMap2");
//		innerList.add(innerMap);
//		testModel.setInnerList(innerList);
////
////
////
//////		log.info("\n原始数据：\n" + testModel.toString());
//////
////		String jsonStr = entityToJson(testModel);
//////		log.info("\n实体类转 JSON：\n" + jsonStr);
//////		Map map = entityToMap(testModel);
//////		log.info("\n实体类转 Map：\n" + map);
//////
//////		log.info("JSON 转 Map：\n" + jsonToMap(jsonStr));
//////		log.info("JSON 转 Entity：\n" + jsonToEntity(jsonStr, TestModel.class));
//////
//////		log.info("\nMap 转 JSON：\n" + mapToJson(map));
//////		log.info(mapToEntity(map, TestModel.class));
////
//		List<TestModel> testModelList = new ArrayList<>();
//		testModelList.add(testModel);
//		String jsonListStr = listToJson(testModelList);
//////		log.info(jsonListStr);
//		log.info(jsonToList(jsonListStr, TestModel.class).toString());
//	}
}
