package com.jd.socialmanager.service;

import com.jd.socialmanager.entity.PoliticalDO;

import java.util.List;

public interface IPoliticalService {

    /**
     * 通过ID查询单条数据
     *
     * @param politicalId 主键
     * @return 实例对象
     */
    PoliticalDO queryById(Integer politicalId);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param political 实例对象
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<PoliticalDO> queryAll(PoliticalDO political, int offset, int limit);

    /**
     * 新增数据
     *
     * @param political 实例对象
     * @return 实例对象
     */
    PoliticalDO insert(PoliticalDO political);

    /**
     * 修改数据
     *
     * @param political 实例对象
     * @return 实例对象
     */
    PoliticalDO update(PoliticalDO political);

    /**
     * 通过主键删除数据
     *
     * @param politicalId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer politicalId);

}
