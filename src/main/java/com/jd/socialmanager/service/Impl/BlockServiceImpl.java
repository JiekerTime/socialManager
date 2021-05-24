package com.jd.socialmanager.service.Impl;

import com.jd.socialmanager.dao.IBlockDao;
import com.jd.socialmanager.entity.BlockDO;
import com.jd.socialmanager.service.IBlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlockServiceImpl implements IBlockService {

    @Autowired
    private IBlockDao blockDao;

    /**
     * 通过ID查询单条数据
     *
     * @param blockId 主键
     * @return 实例对象
     */
    @Override
    public BlockDO queryById(Integer blockId) {
        return this.blockDao.queryById(blockId);
    }


    /**
     * 通过实体作为筛选条件查询
     *
     * @param block  实例对象
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<BlockDO> queryAll(BlockDO block, int offset, int limit) {
        return blockDao.queryAll(block, offset, limit);
    }

    /**
     * 新增数据
     *
     * @param block 实例对象
     * @return 实例对象
     */
    @Override
    public BlockDO insert(BlockDO block) {
        this.blockDao.insert(block);
        return blockDao.queryById(block.getBlockId());
    }

    /**
     * 修改数据
     *
     * @param block 实例对象
     * @return 实例对象
     */
    @Override
    public BlockDO update(BlockDO block) {
        this.blockDao.update(block);
        return this.queryById(block.getBlockId());
    }

    /**
     * 通过主键删除数据
     *
     * @param blockId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer blockId) {
        return this.blockDao.deleteById(blockId) > 0;
    }
}
