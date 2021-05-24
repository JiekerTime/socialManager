package com.jd.socialmanager.service.Impl;

import com.jd.socialmanager.dao.IHostDao;
import com.jd.socialmanager.service.IHostService;
import com.jd.socialmanager.entity.HostDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HostServiceImpl implements IHostService {

    @Autowired
    private IHostDao hostDao;

    /**
     * 通过ID查询单条数据
     *
     * @param hostId 主键
     * @return 实例对象
     */
    @Override
    public HostDO queryById(Integer hostId) {
        return this.hostDao.queryById(hostId);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param uid      身份证号码
     * @param password 密码
     * @return 实例对象
     */
    @Override
    public HostDO findHost(String uid, String password) {
        return hostDao.findHost(uid, password);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param host   实例对象
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<HostDO> queryAll(HostDO host, int offset, int limit) {
        return hostDao.queryAll(host, offset, limit);
    }


    /**
     * 新增数据
     *
     * @param host 实例对象
     * @return 实例对象
     */
    @Override
    public HostDO insert(HostDO host) {
        this.hostDao.insert(host);
        return hostDao.queryById(host.getHostId());
    }

    /**
     * 修改数据
     *
     * @param host 实例对象
     * @return 实例对象
     */
    @Override
    public HostDO update(HostDO host) {
        this.hostDao.update(host);
        return this.queryById(host.getHostId());
    }

    /**
     * 通过主键删除数据
     *
     * @param hostId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer hostId) {
        return this.hostDao.deleteById(hostId) > 0;
    }
}
