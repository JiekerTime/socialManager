package com.jd.socialmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 访客实体类
 *
 * @since 2021-05-19 11:31:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuestDO implements Serializable {
    private static final long serialVersionUID = -69301340137478491L;
    /**
     * 访客编号
     */
    private Integer guestId;
    /**
     * 身份证号
     */
    private String guestUID;
    /**
     * 登记户主
     */
    private Integer hostId;

    private String guestName;

    private String guestMobile;

    private HostDO host;

}
