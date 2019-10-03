package pan.springbootkit.generalmapper.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import pan.springbootkit.base.BaseResult;
import pan.springbootkit.generalmapper.model.User;
import pan.springbootkit.generalmapper.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户 controller
 *
 * Created by panzhangbao on 2018-12-21 16:37:58
 * Copyright © 2018 panzhangbao. All rights reserved.
 */
@RestController
@RequestMapping("api/db/user")
public class UserController {

	@Resource
	private UserService userService;

	/**
	 * 查询列表
	 *
	 * @return pan.springbootkit.generalmapper.base.BaseResult
	 * @date 2019-09-11 22:58
	 * @author panzhangbao
	 */
	@GetMapping("list")
	public BaseResult list(User user) {
		return BaseResult.success(userService.list(user));
	}

	/**
	 * 查询或删除用户
	 *
	 * @param id
	 * @return pan.springbootkit.generalmapper.base.BaseResult
	 * @date 2019-09-11 15:52
	 * @author panzhangbao
	 */
	@GetMapping("{id:\\d+}")
	public BaseResult getOrDelete(@PathVariable("id") Long id) {
		User user = userService.get(id);
		if (null == user) {
			return BaseResult.failure();
		}

		return BaseResult.success(user);
	}

	/**
	 * 新增或更新用户
	 *
	 * @param user
	 * @return pan.springbootkit.generalmapper.base.BaseResult
	 * @date 2019-09-11 15:52
	 * @author panzhangbao
	 */
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
	public BaseResult insertOrUpdate(@RequestBody User user, HttpServletRequest request) {
		/**
		 * 参数合法性校验
		 */
		if (null == user) {
			return BaseResult.neededParam();
		}

		/**
		 * 插入
		 */
		if (request.getMethod().equals(RequestMethod.POST.name())) {
			/**
			 * 参数合法性校验
			 */
			BaseResult baseResult = BaseResult.checkParams(user, "name,phoneNumber");
			if (null != baseResult) {
				return baseResult;
			}

			/**
			 * 根据手机号查询用户是否存在
			 */
			User userCondition = new User();
			userCondition.setPhoneNumber(user.getPhoneNumber());
			userCondition = userService.getByParameters(userCondition);

			if (null != userCondition) {
				return BaseResult.failure("该用户已存在，请勿重复注册！");
			}

			return BaseResult.success(userService.insertOrUpdateSelective(user));
		}

		/**
		 * 更新
		 */
		if (null == user.getId()) {
			return BaseResult.neededParam("id");
		}

		/**
		 * 根据手机号查询用户是否存在
		 */
		if (StringUtils.isNotBlank(user.getPhoneNumber())) {
			User userCondition = new User();
			userCondition.setPhoneNumber(user.getPhoneNumber());
			userCondition = userService.getByParameters(userCondition);
			if (null != userCondition && (userCondition.getId().equals(userCondition.getId()))) {
				return BaseResult.failure("该手机号用户已存在，请换一个手机号！");
			}
		}

		return BaseResult.success(userService.insertOrUpdateSelective(user));
	}

	/**
	 * 批量新增
	 *
	 * @param userList
	 * @return pan.springbootkit.generalmapper.base.BaseResult
	 * @date 2019-09-11 17:36
	 * @author panzhangbao
	 */
	@PostMapping("batch")
	public BaseResult batch(@RequestBody List<User> userList) {
		/**
		 * 参数合法性校验
		 */
		if (CollectionUtils.isEmpty(userList)) {
			return BaseResult.neededParam();
		}

		return BaseResult.success(userService.batchInsert(userList));
	}

	/**
	 * 查询或删除用户
	 *
	 * @param id
	 * @return pan.springbootkit.generalmapper.base.BaseResult
	 * @date 2019-09-11 15:52
	 * @author panzhangbao
	 */
	@DeleteMapping("{id:\\d+}")
	public BaseResult delete(@PathVariable("id") Long id) {
		User user = userService.delete(id);
		if (null == user) {
			return BaseResult.failure();
		}

		return BaseResult.success(user);
	}
}
