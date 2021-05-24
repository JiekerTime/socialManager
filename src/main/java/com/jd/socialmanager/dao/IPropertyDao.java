package com.jd.socialmanager.dao;

import com.jd.socialmanager.entity.PropertyDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IPropertyDao {

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
    List<PropertyDO> queryAll(PropertyDO property, @Param("offset") int offset, @Param("limit") int limit);

    /**
     * 新增数据
     *
     * @param property 实例对象
     * @return 影响行数
     */
    int insert(PropertyDO property);

    /**
     * 修改数据
     *
     * @param property 实例对象
     * @return 影响行数
     */
    int update(PropertyDO property);

    /**
     * 通过主键删除数据
     *
     * @param propertyId 主键
     * @return 影响行数
     */
    int deleteById(Integer propertyId);

}
