package pan.springbootkit.token.controller;

import org.springframework.web.bind.annotation.*;
import pan.springbootkit.base.BaseResult;
import pan.springbootkit.token.service.TokenService;

import javax.annotation.Resource;

/**
 * 测试 token controller
 *
 * Created by panzhangbao on 2019-10-02 10:25:53
 * Copyright © 2019 panzhangbao. All rights reserved.
 */
@RestController
@RequestMapping("api/test/token")
public class TestTokenController {
	@Resource
	private TokenService tokenService;

	@GetMapping
	BaseResult get(@RequestParam(value = "id", required = false) Integer id) {
		if (null == id) {
			id = 1;
		}

		return BaseResult.success(tokenService.createToken(id));
	}

	@DeleteMapping("{id:\\d+}")
	BaseResult delete(@PathVariable("id") Integer id) {
		if (null == id) {
			id = 1;
		}

		tokenService.deleteToken(id);

		return BaseResult.success();
	}
}
