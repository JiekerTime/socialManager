package com.jd.socialmanager.service.Impl;

import com.jd.socialmanager.dao.IPropertyDao;
import com.jd.socialmanager.entity.PropertyDO;
import com.jd.socialmanager.service.IPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PropertyServiceImpl implements IPropertyService {

    @Autowired
    private IPropertyDao propertyDao;

    /**
     * 通过ID查询单条数据
     *
     * @param propertyId 主键
     * @return 实例对象
     */
    @Override
    public PropertyDO queryById(Integer propertyId) {
        return this.propertyDao.queryById(propertyId);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param property 实例对象
     * @param offset   查询起始位置
     * @param limit    查询条数
     * @return 对象列表
     */
    @Override
    public List<PropertyDO> queryAll(PropertyDO property, int offset, int limit) {
        return propertyDao.queryAll(property, offset, limit);
    }

    /**
     * 新增数据
     *
     * @param property 实例对象
     * @return 实例对象
     */
    @Override
    public PropertyDO insert(PropertyDO property) {
        this.propertyDao.insert(property);
        return propertyDao.queryById(property.getPropertyId());
    }

    /**
     * 修改数据
     *
     * @param property 实例对象
     * @return 实例对象
     */
    @Override
    public PropertyDO update(PropertyDO property) {
        this.propertyDao.update(property);
        return this.queryById(property.getPropertyId());
    }

    /**
     * 通过主键删除数据
     *
     * @param propertyId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer propertyId) {
        return this.propertyDao.deleteById(propertyId) > 0;
    }
}
