package com.jd.socialmanager.service;

import com.jd.socialmanager.entity.PetDO;

import java.util.List;

public interface IPetService {

    /**
     * 通过ID查询单条数据
     *
     * @param petId 主键
     * @return 实例对象
     */
    PetDO queryById(Integer petId);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param pet 实例对象
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<PetDO> queryAll(PetDO pet, int offset, int limit);

    /**
     * 新增数据
     *
     * @param pet 实例对象
     * @return 实例对象
     */
    PetDO insert(PetDO pet);

    /**
     * 修改数据
     *
     * @param pet 实例对象
     * @return 实例对象
     */
    PetDO update(PetDO pet);

    /**
     * 通过主键删除数据
     *
     * @param petId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer petId);

}
