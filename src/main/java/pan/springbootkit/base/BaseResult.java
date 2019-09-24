package pan.springbootkit.base;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import pan.springbootkit.generalmapper.base.BaseResultEnum;
import pan.springbootkit.utils.PanStringUtil;
import pan.springbootkit.utils.json.PanJSONUtil;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 基类 Result
 *
 * Created by panzhangbao on 2018-12-24 13:21:32
 * Copyright © 2018 panzhangbao. All rights reserved.
 */
@Data
@NoArgsConstructor
public class BaseResult<T> implements Serializable {

	private static final long serialVersionUID = -1036609680701915312L;

	/**
	 * code
	 */
	private Integer code;

	/**
	 * 提示消息
	 */
	private String message;

	/**
	 * 数据
	 */
	private T data;

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		} else if (!(o instanceof BaseResult)) {
			return false;
		} else {
			BaseResult<?> other = (BaseResult) o;
			if (!other.canEqual(this)) {
				return false;
			} else {
				label47:
				{
					Object this$code = this.getCode();
					Object other$code = other.getCode();
					if (this$code == null) {
						if (other$code == null) {
							break label47;
						}
					} else if (this$code.equals(other$code)) {
						break label47;
					}

					return false;
				}

				Object this$message = this.getMessage();
				Object other$message = other.getMessage();
				if (this$message == null) {
					if (other$message != null) {
						return false;
					}
				} else if (!this$message.equals(other$message)) {
					return false;
				}

				Object this$data = this.getData();
				Object other$data = other.getData();
				if (this$data == null) {
					if (other$data != null) {
						return false;
					}
				} else if (!this$data.equals(other$data)) {
					return false;
				}

				return true;
			}
		}
	}

	protected boolean canEqual(Object other) {
		return other instanceof BaseResult;
	}

	@Override
	public int hashCode() {
		int result = 1;
		Object $code = this.getCode();
		result = result * 59 + ($code == null ? 43 : $code.hashCode());
		Object $message = this.getMessage();
		result = result * 59 + ($message == null ? 43 : $message.hashCode());
		Object $data = this.getData();
		result = result * 59 + ($data == null ? 43 : $data.hashCode());
		return result;
	}

	/**
	 * 成功 0
	 *
	 * @param
	 * @return tc.smartlockapplet.base.BaseResult
	 * @date 2018/12/24 13:29
	 * @author panzhangbao
	 */
	public static BaseResult success() {
		BaseResult baseResult = new BaseResult();
		baseResult.setCode(BaseResultEnum.SUCCESS.getValue());
		baseResult.setMessage(BaseResultEnum.SUCCESS.getMark());

		return baseResult;
	}

	/**
	 * 成功 0
	 *
	 * @param message 消息
	 * @return tc.smartlockapplet.base.BaseResult
	 * @date 2018/12/24 13:29
	 * @author panzhangbao
	 */
	public static BaseResult success(String message) {
		BaseResult baseResult = new BaseResult();
		baseResult.setCode(BaseResultEnum.SUCCESS.getValue());
		baseResult.setMessage(message);

		return baseResult;
	}

	/**
	 * 成功 0
	 *
	 * @param data
	 * @return pan.springbootkit.generalmapper.base.BaseResult
	 * @date 2019-09-11 15:43
	 * @author panzhangbao
	 */
	public static BaseResult success(Object data) {
		BaseResult baseResult = new BaseResult();
		baseResult.setCode(BaseResultEnum.SUCCESS.getValue());
		baseResult.setMessage(BaseResultEnum.SUCCESS.getMark());
		baseResult.setData(data);

		return baseResult;
	}

	/**
	 * 成功 0
	 *
	 * @param message
	 * @param data
	 * @return pan.springbootkit.generalmapper.base.BaseResult
	 * @date 2019-09-11 15:43
	 * @author panzhangbao
	 */
	public static BaseResult success(String message, Object data) {
		BaseResult baseResult = new BaseResult();
		baseResult.setCode(BaseResultEnum.SUCCESS.getValue());
		baseResult.setMessage(message);
		baseResult.setData(data);

		return baseResult;
	}

	/**
	 * 失败 1
	 *
	 * @param
	 * @return tc.smartlockapplet.base.BaseResult
	 * @date 2018/12/25 16:19
	 * @author panzhangbao
	 */
	public static BaseResult failure() {
		BaseResult baseResult = new BaseResult();
		baseResult.setCode(BaseResultEnum.FAILURE.getValue());
		baseResult.setMessage(BaseResultEnum.FAILURE.getMark());

		return baseResult;
	}

	/**
	 * 失败 1
	 *
	 * @param message 消息
	 * @return tc.smartlockapplet.base.BaseResult
	 * @date 2018/12/25 16:19
	 * @author panzhangbao
	 */
	public static BaseResult failure(String message) {
		BaseResult baseResult = new BaseResult();
		baseResult.setCode(BaseResultEnum.FAILURE.getValue());
		baseResult.setMessage(message);

		return baseResult;
	}

	/**
	 * 参数必填 2
	 *
	 * @param
	 * @return tc.smartlockapplet.base.BaseResult
	 * @date 2018/12/25 16:19
	 * @author panzhangbao
	 */
	public static BaseResult neededParam() {
		BaseResult baseResult = new BaseResult();
		baseResult.setCode(BaseResultEnum.NEEDED_PARAMETER.getValue());
		baseResult.setMessage(BaseResultEnum.NEEDED_PARAMETER.getMark());

		return baseResult;
	}

	/**
	 * 参数必填 2
	 *
	 * @param
	 * @return tc.smartlockapplet.base.BaseResult
	 * @date 2018/12/25 16:19
	 * @author panzhangbao
	 */
	public static BaseResult neededParam(String param) {
		BaseResult baseResult = new BaseResult();
		baseResult.setCode(BaseResultEnum.NEEDED_PARAMETER.getValue());
		baseResult.setMessage(param + " " + BaseResultEnum.NEEDED_PARAMETER.getMark());

		return baseResult;
	}

	/**
	 * 参数必填校验 2
	 *
	 * @param o	实体类对象
	 * @param paramKeys	前端必填参数 keys
	 * @return pan.springbootkit.generalmapper.base.BaseResult
	 * @date 2019-09-12 00:02
	 * @author panzhangbao
	 */
	public static BaseResult checkParams(Object o, String paramKeys) {
		if (null == o || StringUtils.isBlank(paramKeys)) {
			return BaseResult.neededParam();
		}

		Map entityMap = PanJSONUtil.entityToMap(o);
		entityMap.remove("isDeleted");


		List<String> paramList = PanStringUtil.stringToList(paramKeys);
		StringBuilder neededParams = new StringBuilder();
		for (String key : paramList) {
			if (entityMap.containsKey(key) && null != entityMap.get(key)) {
				continue;
			}

			if (StringUtils.isNotBlank(neededParams.toString())) {
				neededParams.append(",");
			}
			neededParams.append(key);
		}

		if (StringUtils.isNotBlank(neededParams.toString())) {
			return BaseResult.neededParam(neededParams.toString());
		}

		return null;
	}

	/**
	 * 参数不合法 3
	 *
	 * @param
	 * @return pan.springbootkit.generalmapper.base.BaseResult
	 * @date 2018/12/25 23:33
	 * @author panzhangbao
	 */
	public static BaseResult illegalParam() {
		BaseResult baseResult = new BaseResult();
		baseResult.setCode(BaseResultEnum.ILLEGAL_PARAMETER.getValue());
		baseResult.setMessage(BaseResultEnum.ILLEGAL_PARAMETER.getMark());

		return baseResult;
	}

	/**
	 * 参数不合法 3
	 *
	 * @param param 参数
	 * @return pan.springbootkit.generalmapper.base.BaseResult
	 * @date 2018/12/25 23:33
	 * @author panzhangbao
	 */
	public static BaseResult illegalParam(String param) {
		BaseResult baseResult = new BaseResult();
		baseResult.setCode(BaseResultEnum.ILLEGAL_PARAMETER.getValue());
		baseResult.setMessage(param + " " + BaseResultEnum.ILLEGAL_PARAMETER.getMark());

		return baseResult;
	}


	/**
	 * 系统错误 -1
	 *
	 * @param e 异常
	 * @return pan.springbootkit.generalmapper.base.BaseResult
	 * @date 2018/12/25 16:19
	 * @author panzhangbao
	 */
	public static BaseResult systemError(Exception e) {
		BaseResult baseResult = new BaseResult();
		baseResult.setCode(BaseResultEnum.SYSTEM_ERROR.getValue());
		baseResult.setMessage(BaseResultEnum.SYSTEM_ERROR.getMark());
		baseResult.setData("接口访问异常: " + e.toString());

		return baseResult;
	}
}
