package com.jd.socialmanager.service.Impl;

import com.jd.socialmanager.dao.IPoliticalDao;
import com.jd.socialmanager.entity.PoliticalDO;
import com.jd.socialmanager.service.IPoliticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PoliticalServiceImpl implements IPoliticalService {

    @Autowired
    private IPoliticalDao politicalDao;

    /**
     * 通过ID查询单条数据
     *
     * @param politicalId 主键
     * @return 实例对象
     */
    @Override
    public PoliticalDO queryById(Integer politicalId) {
        return this.politicalDao.queryById(politicalId);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param political 实例对象
     * @param offset    查询起始位置
     * @param limit     查询条数
     * @return 对象列表
     */
    @Override
    public List<PoliticalDO> queryAll(PoliticalDO political, int offset, int limit) {
        return politicalDao.queryAll(political, offset, limit);
    }

    /**
     * 新增数据
     *
     * @param political 实例对象
     * @return 实例对象
     */
    @Override
    public PoliticalDO insert(PoliticalDO political) {
        this.politicalDao.insert(political);
        return politicalDao.queryById(political.getPoliticalId());
    }

    /**
     * 修改数据
     *
     * @param political 实例对象
     * @return 实例对象
     */
    @Override
    public PoliticalDO update(PoliticalDO political) {
        this.politicalDao.update(political);
        return this.queryById(political.getPoliticalId());
    }

    /**
     * 通过主键删除数据
     *
     * @param politicalId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer politicalId) {
        return this.politicalDao.deleteById(politicalId) > 0;
    }
}
