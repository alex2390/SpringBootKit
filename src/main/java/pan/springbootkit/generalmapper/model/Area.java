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
 * 区域 实体类
 *
 * Created by panzhangbao on 2019-10-04 00:08:40
 * Copyright © 2018 panzhangbao. All rights reserved.
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Table(name = "area")
public class Area extends BaseModel implements Serializable {

	private static final long serialVersionUID = -5550777596951862851L;

	/**
     * 名称
     */
    private String name;

    /**
     * 级别（默认为1。1：省；2：市；3：县：4：镇；5：村）
     */
    private Integer level;

    /**
     * 父级 id（默认为 0）
     */
    @Column(name = "parent_id")
    private Integer parentId;
}