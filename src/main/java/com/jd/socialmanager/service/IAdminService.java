package com.jd.socialmanager.service;

import com.jd.socialmanager.entity.Admin;

import java.util.List;

public interface IAdminService {

    /**
     * 登录用户信息
     *
     * @param admin 实例对象
     * @return 实例对象
     */
    Admin findAdmin(Admin admin);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param admin 实例对象
     * @return 对象列表
     */
    List<Admin> queryAll(Admin admin, int offset, int limit);

    /**
     * 新增数据
     *
     * @param admin 实例对象
     * @return 实例对象
     */
    Admin insert(Admin admin);

    /**
     * 修改数据
     *
     * @param admin 实例对象
     * @return 实例对象
     */
    Admin changePassword(Admin admin);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
