package com.jd.socialmanager.dao;

import com.jd.socialmanager.entity.GuestDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IGuestDao {

    /**
     * 通过ID查询单条数据
     *
     * @param guestId 主键
     * @return 实例对象
     */
    GuestDO queryById(Integer guestId);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param guest 实例对象
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<GuestDO> queryAll(GuestDO guest, @Param("offset") int offset, @Param("limit") int limit);

    /**
     * 新增数据
     *
     * @param guest 实例对象
     * @return 影响行数
     */
    int insert(GuestDO guest);

    /**
     * 修改数据
     *
     * @param guest 实例对象
     * @return 影响行数
     */
    int update(GuestDO guest);

    /**
     * 通过主键删除数据
     *
     * @param guestId 主键
     * @return 影响行数
     */
    int deleteById(Integer guestId);

}
