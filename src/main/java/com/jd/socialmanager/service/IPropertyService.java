package com.jd.socialmanager.service;

import com.jd.socialmanager.entity.PropertyDO;

import java.util.List;

public interface IPropertyService {

    /**
     * 通过ID查询单条数据
     *
     * @param propertyId 主键
     * @return 实例对象
     */
    PropertyDO queryById(Integer propertyId);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param property 实例对象
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<PropertyDO> queryAll(PropertyDO property, int offset, int limit);

    /**
     * 新增数据
     *
     * @param property 实例对象
     * @return 实例对象
     */
    PropertyDO insert(PropertyDO property);

    /**
     * 修改数据
     *
     * @param property 实例对象
     * @return 实例对象
     */
    PropertyDO update(PropertyDO property);

    /**
     * 通过主键删除数据
     *
     * @param propertyId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer propertyId);

}
