package com.jd.socialmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 物业实体类
 *
 * @since 2021-05-19 11:31:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyDO implements Serializable {
    private static final long serialVersionUID = -20589425316074037L;

    private Integer propertyId;
    /**
     * 所属区号
     */
    private Integer blockId;
    /**
     * 物业身份证号
     */
    private String uid;
    /**
     * 物业名称
     */
    private String propertyName;

    private BlockDO block;

}
