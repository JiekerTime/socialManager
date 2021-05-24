package com.jd.socialmanager.service.Impl;

import com.jd.socialmanager.dao.IGuestDao;
import com.jd.socialmanager.entity.GuestDO;
import com.jd.socialmanager.service.IGuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestServiceImpl implements IGuestService {

    @Autowired
    private IGuestDao guestDao;

    /**
     * 通过ID查询单条数据
     *
     * @param guestId 主键
     * @return 实例对象
     */
    @Override
    public GuestDO queryById(Integer guestId) {
        return this.guestDao.queryById(guestId);
    }


    /**
     * 通过实体作为筛选条件查询
     *
     * @param guest  实例对象
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<GuestDO> queryAll(GuestDO guest, int offset, int limit) {
        return guestDao.queryAll(guest, offset, limit);
    }

    /**
     * 新增数据
     *
     * @param guest 实例对象
     * @return 实例对象
     */
    @Override
    public GuestDO insert(GuestDO guest) {
        this.guestDao.insert(guest);
        return guestDao.queryById(guest.getGuestId());
    }

    /**
     * 修改数据
     *
     * @param guest 实例对象
     * @return 实例对象
     */
    @Override
    public GuestDO update(GuestDO guest) {
        this.guestDao.update(guest);
        return this.queryById(guest.getGuestId());
    }

    /**
     * 通过主键删除数据
     *
     * @param guestId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer guestId) {
        return this.guestDao.deleteById(guestId) > 0;
    }
}
