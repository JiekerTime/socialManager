package com.jd.socialmanager.service.Impl;

import com.jd.socialmanager.dao.IParkDao;
import com.jd.socialmanager.entity.ParkDO;
import com.jd.socialmanager.service.IParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkServiceImpl implements IParkService {

    @Autowired
    private IParkDao partDao;

    /**
     * 通过ID查询单条数据
     *
     * @param parkId 主键
     * @return 实例对象
     */
    @Override
    public ParkDO queryById(Integer parkId) {
        return this.partDao.queryById(parkId);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param park   实例对象
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<ParkDO> queryAll(ParkDO park, int offset, int limit) {
        return partDao.queryAll(park, offset, limit);
    }

    /**
     * 新增数据
     *
     * @param part 实例对象
     * @return 实例对象
     */
    @Override
    public ParkDO insert(ParkDO part) {
        this.partDao.insert(part);
        return partDao.queryById(part.getParkId());
    }

    /**
     * 修改数据
     *
     * @param part 实例对象
     * @return 实例对象
     */
    @Override
    public ParkDO update(ParkDO part) {
        this.partDao.update(part);
        return this.queryById(part.getParkId());
    }

    /**
     * 通过主键删除数据
     *
     * @param parkId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer parkId) {
        return this.partDao.deleteById(parkId) > 0;
    }
}
