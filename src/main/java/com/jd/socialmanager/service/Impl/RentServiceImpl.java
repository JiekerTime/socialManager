package com.jd.socialmanager.service.Impl;

import com.jd.socialmanager.dao.IRentDao;
import com.jd.socialmanager.entity.RentDO;
import com.jd.socialmanager.service.IRentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentServiceImpl implements IRentService {

    @Autowired
    private IRentDao rentDao;

    /**
     * 通过ID查询单条数据
     *
     * @param rentId 主键
     * @return 实例对象
     */
    @Override
    public RentDO queryById(Integer rentId) {
        return this.rentDao.queryById(rentId);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param rent   实例对象
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<RentDO> queryAll(RentDO rent, int offset, int limit) {
        return rentDao.queryAll(rent, offset, limit);
    }


    /**
     * 新增数据
     *
     * @param rent 实例对象
     * @return 实例对象
     */
    @Override
    public RentDO insert(RentDO rent) {
        this.rentDao.insert(rent);
        return rentDao.queryById(rent.getRentId());
    }

    /**
     * 修改数据
     *
     * @param rent 实例对象
     * @return 实例对象
     */
    @Override
    public RentDO update(RentDO rent) {
        this.rentDao.update(rent);
        return this.queryById(rent.getRentId());
    }

    /**
     * 通过主键删除数据
     *
     * @param rentId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer rentId) {
        return this.rentDao.deleteById(rentId) > 0;
    }
}
