package com.jd.socialmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 停车实体类
 *
 * @since 2021-05-19 11:31:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkDO implements Serializable {
    private static final long serialVersionUID = 278352318671712685L;

    private Integer parkId;

    private Integer blockId;

    private BlockDO block;

    private PropertyDO property;

}
