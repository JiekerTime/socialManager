package com.jd.socialmanager.service.Impl;

import com.jd.socialmanager.dao.IStoreDao;
import com.jd.socialmanager.entity.StoreDO;
import com.jd.socialmanager.service.IStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements IStoreService {

    @Autowired
    private IStoreDao storeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param storeId 主键
     * @return 实例对象
     */
    @Override
    public StoreDO queryById(Integer storeId) {
        return this.storeDao.queryById(storeId);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param store  实例对象
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<StoreDO> queryAll(StoreDO store, int offset, int limit) {
        return storeDao.queryAll(store, offset, limit);
    }

    /**
     * 新增数据
     *
     * @param store 实例对象
     * @return 实例对象
     */
    @Override
    public StoreDO insert(StoreDO store) {
        this.storeDao.insert(store);
        return storeDao.queryById(store.getStoreId());
    }

    /**
     * 修改数据
     *
     * @param store 实例对象
     * @return 实例对象
     */
    @Override
    public StoreDO update(StoreDO store) {
        this.storeDao.update(store);
        return this.queryById(store.getStoreId());
    }

    /**
     * 通过主键删除数据
     *
     * @param storeId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer storeId) {
        return this.storeDao.deleteById(storeId) > 0;
    }
}
