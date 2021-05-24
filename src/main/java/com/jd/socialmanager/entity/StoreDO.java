package com.jd.socialmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 商店实体类
 *
 * @since 2021-05-19 11:31:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreDO implements Serializable {
    private static final long serialVersionUID = 205455039482568429L;

    private Integer storeId;
    /**
     * 业主ID
     */
    private Integer hostId;
    /**
     * 底商名称
     */
    private String storeName;
    /**
     * 底商类型
     */
    private String storeType;

    private HostDO host;

}
