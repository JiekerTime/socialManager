package com.jd.socialmanager.service;

import com.jd.socialmanager.entity.GuestDO;

import java.util.List;

public interface IGuestService {

    /**
     * 通过ID查询单条数据
     *
     * @param guestId 主键
     * @return 实例对象
     */
    GuestDO queryById(Integer guestId);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param guest 实例对象
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<GuestDO> queryAll(GuestDO guest, int offset, int limit);


    /**
     * 新增数据
     *
     * @param guest 实例对象
     * @return 实例对象
     */
    GuestDO insert(GuestDO guest);

    /**
     * 修改数据
     *
     * @param guest 实例对象
     * @return 实例对象
     */
    GuestDO update(GuestDO guest);

    /**
     * 通过主键删除数据
     *
     * @param guestId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer guestId);

}
