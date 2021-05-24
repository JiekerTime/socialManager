package com.jd.socialmanager.service.Impl;

import com.jd.socialmanager.dao.ISecurityDao;
import com.jd.socialmanager.entity.SecurityDO;
import com.jd.socialmanager.service.ISecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecurityServiceImpl implements ISecurityService {

    @Autowired
    private ISecurityDao securityDao;

    /**
     * 通过ID查询单条数据
     *
     * @param securityId 主键
     * @return 实例对象
     */
    @Override
    public SecurityDO queryById(Integer securityId) {
        return this.securityDao.queryById(securityId);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param security 实例对象
     * @param offset   查询起始位置
     * @param limit    查询条数
     * @return 对象列表
     */
    @Override
    public List<SecurityDO> queryAll(SecurityDO security, int offset, int limit) {
        return securityDao.queryAll(security, offset, limit);
    }

    /**
     * 新增数据
     *
     * @param security 实例对象
     * @return 实例对象
     */
    @Override
    public SecurityDO insert(SecurityDO security) {
        this.securityDao.insert(security);
        return securityDao.queryById(security.getSecurityId());
    }

    /**
     * 修改数据
     *
     * @param security 实例对象
     * @return 实例对象
     */
    @Override
    public SecurityDO update(SecurityDO security) {
        this.securityDao.update(security);
        return this.queryById(security.getSecurityId());
    }

    /**
     * 通过主键删除数据
     *
     * @param securityId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer securityId) {
        return this.securityDao.deleteById(securityId) > 0;
    }
}
