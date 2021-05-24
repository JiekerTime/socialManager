package com.jd.socialmanager.dao;

import com.jd.socialmanager.entity.PetDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IPetDao {

    /**
     * 通过ID查询单条数据
     *
     * @param petId 主键
     * @return 实例对象
     */
    PetDO queryById(Integer petId);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param pet 实例对象
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<PetDO> queryAll(PetDO pet, @Param("offset") int offset, @Param("limit") int limit);

    /**
     * 新增数据
     *
     * @param pet 实例对象
     * @return 影响行数
     */
    int insert(PetDO pet);

    /**
     * 修改数据
     *
     * @param pet 实例对象
     * @return 影响行数
     */
    int update(PetDO pet);

    /**
     * 通过主键删除数据
     *
     * @param petId 主键
     * @return 影响行数
     */
    int deleteById(Integer petId);

}
