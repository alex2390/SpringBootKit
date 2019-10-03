package pan.springbootkit.generalmapper.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * 基类 model
 *
 * Created by panzhangbao on 2018-12-23 23:12:47
 * Copyright © 2018 panzhangbao. All rights reserved.
 */
@Data
@Component
public class BaseModel implements Serializable {

	private static final long serialVersionUID = -8149178435557670884L;

	public static final Boolean DELETED_TRUE = true;
	public static final Boolean DELETED_FALSE = false;
	public static final Integer PAGE_NUM_DEFAULT = 1;
	public static final Integer PAGE_SIZE_DEFAULT = 10;

	/**
	 * 主键
	 * 	@GeneratedValue(strategy = GenerationType.IDENTITY) 插入后返回 id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/**
	 * 更新时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

	/**
	 * 伪删除标记字段（0：正常；1：伪删除）
	 */
	@JsonIgnore
	private Boolean isDeleted;

	/**
	 * 第几页
	 */
	@JsonIgnore
	@Transient
	private Integer pageNum;

	/**
	 * 每页大小
	 */
	@JsonIgnore
	@Transient
	private Integer pageSize;

	public BaseModel() {
		this.isDeleted = DELETED_FALSE;
	}

	@Override
	public String toString() {
		return "BaseModel(super=" + super.toString() + ", id=" + this.getId() + ", createTime=" + this.getCreateTime() + ", updateTime=" + this.getUpdateTime() + ", isDeleted=" + this.getIsDeleted() + ", pageNum=" + this.getPageNum() + ", pageSize=" + this.getPageSize() + ")";
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		} else if (!(o instanceof BaseModel)) {
			return false;
		} else {
			BaseModel other = (BaseModel) o;
			if (!other.canEqual(this)) {
				return false;
			} else {
				Object this$id = this.getId();
				Object other$id = other.getId();
				if (this$id == null) {
					if (other$id != null) {
						return false;
					}
				} else if (!this$id.equals(other$id)) {
					return false;
				}

				Object this$createTime = this.getCreateTime();
				Object other$createTime = other.getCreateTime();
				if (this$createTime == null) {
					if (other$createTime != null) {
						return false;
					}
				} else if (!this$createTime.equals(other$createTime)) {
					return false;
				}

				Object this$updateTime = this.getUpdateTime();
				Object other$updateTime = other.getUpdateTime();
				if (this$updateTime == null) {
					if (other$updateTime != null) {
						return false;
					}
				} else if (!this$updateTime.equals(other$updateTime)) {
					return false;
				}

				label62:
				{
					Object this$isDeleted = this.getIsDeleted();
					Object other$isDeleted = other.getIsDeleted();
					if (this$isDeleted == null) {
						if (other$isDeleted == null) {
							break label62;
						}
					} else if (this$isDeleted.equals(other$isDeleted)) {
						break label62;
					}

					return false;
				}

				label55:
				{
					Object this$pageNum = this.getPageNum();
					Object other$pageNum = other.getPageNum();
					if (this$pageNum == null) {
						if (other$pageNum == null) {
							break label55;
						}
					} else if (this$pageNum.equals(other$pageNum)) {
						break label55;
					}

					return false;
				}

				Object this$pageSize = this.getPageSize();
				Object other$pageSize = other.getPageSize();
				if (this$pageSize == null) {
					if (other$pageSize != null) {
						return false;
					}
				} else if (!this$pageSize.equals(other$pageSize)) {
					return false;
				}

				return true;
			}
		}
	}

	protected boolean canEqual(Object other) {
		return other instanceof BaseModel;
	}

	@Override
	public int hashCode() {
		int result = 1;
		Object $id = this.getId();
		result = result * 59 + ($id == null ? 43 : $id.hashCode());
		Object $createTime = this.getCreateTime();
		result = result * 59 + ($createTime == null ? 43 : $createTime.hashCode());
		Object $updateTime = this.getUpdateTime();
		result = result * 59 + ($updateTime == null ? 43 : $updateTime.hashCode());
		Object $isDeleted = this.getIsDeleted();
		result = result * 59 + ($isDeleted == null ? 43 : $isDeleted.hashCode());
		Object $pageNum = this.getPageNum();
		result = result * 59 + ($pageNum == null ? 43 : $pageNum.hashCode());
		Object $pageSize = this.getPageSize();
		result = result * 59 + ($pageSize == null ? 43 : $pageSize.hashCode());
		return result;
	}
}

