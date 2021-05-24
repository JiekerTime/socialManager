package com.jd.socialmanager.dao;

import com.jd.socialmanager.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IAdminDao {

    /**
     * 登录用户
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
    List<Admin> queryAll(Admin admin, @Param("offset") int offset, @Param("limit") int limit);

    /**
     * 新增数据
     *
     * @param admin 实例对象
     * @return 影响行数
     */
    int insert(Admin admin);

    /**
     * 修改用户密码
     *
     * @param admin 实例对象
     * @return 影响行数
     */
    int changePassword(Admin admin);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}
