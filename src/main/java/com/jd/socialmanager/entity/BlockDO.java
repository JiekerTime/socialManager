package com.jd.socialmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 区块实体类
 *
 * @since 2021-05-19 11:31:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlockDO implements Serializable {
    private static final long serialVersionUID = 219347299873370227L;

    private Integer blockId;
    /**
     * 区名，如（文明楼）
     */
    private String blockName;
}
