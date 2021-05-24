package com.jd.socialmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 业主实体类
 *
 * @since 2021-05-19 11:31:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HostDO implements Serializable {
    private static final long serialVersionUID = 131805171381189130L;
    /**
     * 户主编号
     */
    private Integer hostId;
    /**
     * 身份证号
     */
    private String uid;
    /**
     * 区号
     */
    private Integer blockId;
    /**
     * 政治编号
     */
    private Integer politicalId;
    /**
     * 租赁编号（0为空）
     */
    private Integer rentId;
    /**
     * 宠物编号（0为空）
     */
    private Integer petId;
    /**
     * 停车场编号
     */
    private Integer parkId;

    private String hostName;

    private String password;

    private Integer roomId;

    private String mobile;

    private BlockDO block;

    private PoliticalDO political;

    private RentDO rent;

    private PetDO pet;

}
