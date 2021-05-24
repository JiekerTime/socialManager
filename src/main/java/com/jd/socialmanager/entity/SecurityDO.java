package com.jd.socialmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 安保实体类
 *
 * @since 2021-05-19 11:31:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SecurityDO implements Serializable {
    private static final long serialVersionUID = 552357180188675047L;
    /**
     * 安保编号
     */
    private Integer securityId;
    /**
     * 安保人员身份证号
     */
    private String uid;
    /**
     * 所属区号
     */
    private Integer blockId;

    private String securityName;

    private BlockDO block;

}
