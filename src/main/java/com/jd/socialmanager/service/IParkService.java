package com.jd.socialmanager.service;

import com.jd.socialmanager.entity.ParkDO;

import java.util.List;

public interface IParkService {

    /**
     * 通过ID查询单条数据
     *
     * @param parkId 主键
     * @return 实例对象
     */
    ParkDO queryById(Integer parkId);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param park 实例对象
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<ParkDO> queryAll(ParkDO park, int offset, int limit);

    /**
     * 新增数据
     *
     * @param part 实例对象
     * @return 实例对象
     */
    ParkDO insert(ParkDO part);

    /**
     * 修改数据
     *
     * @param part 实例对象
     * @return 实例对象
     */
    ParkDO update(ParkDO part);

    /**
     * 通过主键删除数据
     *
     * @param parkId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer parkId);

}
