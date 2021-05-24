package com.jd.socialmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 政治面貌实体类
 *
 * @since 2021-05-19 11:31:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PoliticalDO implements Serializable {
    private static final long serialVersionUID = 925679568175757598L;
    /**
     * 政治编号
     */
    private Integer politicalId;
    /**
     * 政治类型
     */
    private String politicalType;
    /**
     * 费用
     */
    private Object charge;

}
