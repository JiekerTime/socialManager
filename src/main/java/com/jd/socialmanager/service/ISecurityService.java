package com.jd.socialmanager.service;

import com.jd.socialmanager.entity.SecurityDO;

import java.util.List;

public interface ISecurityService {

    /**
     * 通过ID查询单条数据
     *
     * @param securityId 主键
     * @return 实例对象
     */
    SecurityDO queryById(Integer securityId);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param security 实例对象
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SecurityDO> queryAll(SecurityDO security, int offset, int limit);

    /**
     * 新增数据
     *
     * @param security 实例对象
     * @return 实例对象
     */
    SecurityDO insert(SecurityDO security);

    /**
     * 修改数据
     *
     * @param security 实例对象
     * @return 实例对象
     */
    SecurityDO update(SecurityDO security);

    /**
     * 通过主键删除数据
     *
     * @param securityId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer securityId);

}
