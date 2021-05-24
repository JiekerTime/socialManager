package com.jd.socialmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 宠物实体类
 *
 * @since 2021-05-19 11:31:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetDO implements Serializable {
    private static final long serialVersionUID = -18524967637251403L;

    private Integer petId;
    /**
     * 所属户主
     */
    private Integer hostId;
    /**
     * 宠物名称
     */
    private String petName;
    /**
     * 宠物类型
     */
    private String petType;

    private HostDO host;

}
