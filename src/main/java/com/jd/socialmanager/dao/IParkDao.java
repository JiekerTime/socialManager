package com.jd.socialmanager.dao;

import com.jd.socialmanager.entity.ParkDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IParkDao {

    /**
     * 通过ID查询单条数据
     *
     * @param parkId 主键
     * @return 实例对象
     */
    ParkDO queryById(Integer parkId);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param park 实例对象
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<ParkDO> queryAll(ParkDO park, @Param("offset") int offset, @Param("limit") int limit);

    /**
     * 新增数据
     *
     * @param part 实例对象
     * @return 影响行数
     */
    int insert(ParkDO part);

    /**
     * 修改数据
     *
     * @param part 实例对象
     * @return 影响行数
     */
    int update(ParkDO part);

    /**
     * 通过主键删除数据
     *
     * @param parkId 主键
     * @return 影响行数
     */
    int deleteById(Integer parkId);

}
