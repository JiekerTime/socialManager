package com.jd.socialmanager.service;

import com.jd.socialmanager.entity.BaseDO;

import java.util.List;

public interface IBaseService {

    /**
     * 通过ID查询单条数据
     *
     * @param baseId 主键
     * @return 对象数量
     */
    BaseDO queryById(Integer baseId);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param baseDO 实例对象
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<BaseDO> queryAll(BaseDO baseDO, int offset, int limit);


    /**
     * 新增数据
     *
     * @param baseDO 实例对象
     * @return 实例对象
     */
    BaseDO insert(BaseDO baseDO);

    /**
     * 修改数据
     *
     * @param baseDO 实例对象
     * @return 实例对象
     */
    BaseDO update(BaseDO baseDO);

    /**
     * 通过主键删除数据
     *
     * @param baseId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer baseId);

}
