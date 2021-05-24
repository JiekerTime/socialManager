package com.jd.socialmanager.service;

import com.jd.socialmanager.entity.HostDO;

import java.util.List;

public interface IHostService {

    /**
     * 通过ID查询单条数据
     *
     * @param hostId 主键
     * @return 实例对象
     */
    HostDO queryById(Integer hostId);

    /**
     * 通过ID查询单条数据
     *
     * @param uid      身份证号码
     * @param password 密码
     * @return 实例对象
     */
    HostDO findHost(String uid, String password);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param host   实例对象
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<HostDO> queryAll(HostDO host, int offset, int limit);

    /**
     * 新增数据
     *
     * @param host 实例对象
     * @return 实例对象
     */
    HostDO insert(HostDO host);

    /**
     * 修改数据
     *
     * @param host 实例对象
     * @return 实例对象
     */
    HostDO update(HostDO host);

    /**
     * 通过主键删除数据
     *
     * @param hostId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer hostId);

}
