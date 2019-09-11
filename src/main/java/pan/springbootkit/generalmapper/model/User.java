package pan.springbootkit.generalmapper.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import pan.springbootkit.generalmapper.base.BaseModel;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 用户 实体类
 *
 * Created by panzhangbao on 2018-12-24 12:08:59
 * Copyright © 2018 panzhangbao. All rights reserved.
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Table(name = "tb_user")
public class User extends BaseModel implements Serializable {

	private static final long serialVersionUID = -7377646896252514022L;

	/**
     * 姓名
     */
    private String name;

    /**
     * 性别（0:未知;1:男:2:女）
     */
    private Integer gender;

    /**
     * 手机号
     */
    @Column(name = "phone_number")
    private String phoneNumber;
}