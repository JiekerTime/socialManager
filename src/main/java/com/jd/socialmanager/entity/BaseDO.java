package com.jd.socialmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 基础设施类
 *
 * @since 2021-05-19 11:31:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseDO implements Serializable {
    private static final long serialVersionUID = -89677124251302867L;

    private Integer baseId;
    /**
     * 所属区号
     */
    private Integer blockId;
    /**
     * 基础设施名称
     */
    private String baseName;
    /**
     * 基础设施状态
     */
    private String status;

    private BlockDO block;
}
