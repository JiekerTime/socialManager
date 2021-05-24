package com.jd.socialmanager.service.Impl;

import com.jd.socialmanager.dao.IAdminDao;
import com.jd.socialmanager.entity.Admin;
import com.jd.socialmanager.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements IAdminService {

    @Autowired
    private IAdminDao adminDao;

    /**
     * 通过ID查询单条数据
     *
     * @param admin 实例对象
     * @return 实例对象
     */
    @Override
    public Admin findAdmin(Admin admin) {
        return adminDao.findAdmin(admin);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param admin 实例对象
     * @return 对象列表
     */
    @Override
    public List<Admin> queryAll(Admin admin, int offset, int limit) {
        return adminDao.queryAll(admin, offset, limit);
    }

    /**
     * 新增数据
     *
     * @param admin 实例对象
     * @return 实例对象
     */
    @Override
    public Admin insert(Admin admin) {
        adminDao.insert(admin);
        return admin;
    }

    /**
     * 修改数据
     *
     * @param admin 实例对象
     * @return 实例对象
     */
    @Override
    public Admin changePassword(Admin admin) {
        adminDao.changePassword(admin);
        return adminDao.findAdmin(admin);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return adminDao.deleteById(id) > 0;
    }
}
