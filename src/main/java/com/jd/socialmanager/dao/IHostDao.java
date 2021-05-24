package com.jd.socialmanager.dao;

import com.jd.socialmanager.entity.HostDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IHostDao {

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
    HostDO findHost(@Param("uid") String uid, @Param("password") String password);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param host   实例对象
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<HostDO> queryAll(HostDO host, @Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param host 实例对象
     * @return 对象列表
     */
    List<HostDO> queryAll(HostDO host);

    /**
     * 新增数据
     *
     * @param host 实例对象
     * @return 影响行数
     */
    int insert(HostDO host);

    /**
     * 修改数据
     *
     * @param host 实例对象
     * @return 影响行数
     */
    int update(HostDO host);

    /**
     * 通过主键删除数据
     *
     * @param hostId 主键
     * @return 影响行数
     */
    int deleteById(Integer hostId);

}
