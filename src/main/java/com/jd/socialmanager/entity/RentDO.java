package com.jd.socialmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 租户实体类
 *
 * @since 2021-05-19 11:31:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentDO implements Serializable {
    private static final long serialVersionUID = -79204305232914918L;
    /**
     * 租赁编号
     */
    private Integer rentId;
    /**
     * 业主编号
     */
    private Integer hostId;
    /**
     * 租客身份证号
     */
    private String rentUid;
    /**
     * 租客名称
     */
    private String rentName;

    private HostDO host;

}
