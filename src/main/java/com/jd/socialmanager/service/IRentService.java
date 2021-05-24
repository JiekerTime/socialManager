package com.jd.socialmanager.service;

import com.jd.socialmanager.entity.RentDO;

import java.util.List;

public interface IRentService {

    /**
     * 通过ID查询单条数据
     *
     * @param rentId 主键
     * @return 实例对象
     */
    RentDO queryById(Integer rentId);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param rent 实例对象
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<RentDO> queryAll(RentDO rent, int offset, int limit);

    /**
     * 新增数据
     *
     * @param rent 实例对象
     * @return 实例对象
     */
    RentDO insert(RentDO rent);

    /**
     * 修改数据
     *
     * @param rent 实例对象
     * @return 实例对象
     */
    RentDO update(RentDO rent);

    /**
     * 通过主键删除数据
     *
     * @param rentId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer rentId);

}
