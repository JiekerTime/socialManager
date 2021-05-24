package com.jd.socialmanager.service.Impl;

import com.jd.socialmanager.dao.IBaseDao;
import com.jd.socialmanager.entity.BaseDO;
import com.jd.socialmanager.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseServiceImpl implements IBaseService {

    @Autowired
    private IBaseDao baseDao;

    /**
     * 通过ID查询单条数据
     *
     * @param baseId 主键
     * @return 实例对象
     */
    @Override
    public BaseDO queryById(Integer baseId) {
        return baseDao.queryById(baseId);
    }


    /**
     * 通过实体作为筛选条件查询
     *
     * @param baseDO 实例对象
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<BaseDO> queryAll(BaseDO baseDO, int offset, int limit) {
        return baseDao.queryAll(baseDO, offset, limit);
    }

    /**
     * 新增数据
     *
     * @param base 实例对象
     * @return 实例对象
     */
    @Override
    public BaseDO insert(BaseDO base) {
        baseDao.insert(base);
        return baseDao.queryById(base.getBaseId());
    }

    /**
     * 修改数据
     *
     * @param base 实例对象
     * @return 实例对象
     */
    @Override
    public BaseDO update(BaseDO base) {
        baseDao.update(base);
        return baseDao.queryById(base.getBaseId());
    }

    /**
     * 通过主键删除数据
     *
     * @param baseId 主键
     * @return 实例对象
     */
    @Override
    public boolean deleteById(Integer baseId) {
        return baseDao.deleteById(baseId) > 0;
    }
}
