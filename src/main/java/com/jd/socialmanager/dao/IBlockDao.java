package com.jd.socialmanager.dao;

import com.jd.socialmanager.entity.BlockDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IBlockDao {

    /**
     * 通过ID查询单条数据
     *
     * @param blockId 主键
     * @return 实例对象
     */
    BlockDO queryById(Integer blockId);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param block 实例对象
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<BlockDO> queryAll(BlockDO block, @Param("offset") int offset, @Param("limit") int limit);

    /**
     * 新增数据
     *
     * @param block 实例对象
     * @return 影响行数
     */
    int insert(BlockDO block);

    /**
     * 修改数据
     *
     * @param block 实例对象
     * @return 影响行数
     */
    int update(BlockDO block);

    /**
     * 通过主键删除数据
     *
     * @param blockId 主键
     * @return 影响行数
     */
    int deleteById(Integer blockId);

}
