package com.jd.socialmanager.service.Impl;

import com.jd.socialmanager.dao.IPetDao;
import com.jd.socialmanager.entity.PetDO;
import com.jd.socialmanager.service.IPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServiceImpl implements IPetService {

    @Autowired
    private IPetDao petDao;

    /**
     * 通过ID查询单条数据
     *
     * @param petId 主键
     * @return 实例对象
     */
    @Override
    public PetDO queryById(Integer petId) {
        return this.petDao.queryById(petId);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param pet    实例对象
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<PetDO> queryAll(PetDO pet, int offset, int limit) {
        return petDao.queryAll(pet, offset, limit);
    }

    /**
     * 新增数据
     *
     * @param pet 实例对象
     * @return 实例对象
     */
    @Override
    public PetDO insert(PetDO pet) {
        this.petDao.insert(pet);
        return petDao.queryById(pet.getPetId());
    }

    /**
     * 修改数据
     *
     * @param pet 实例对象
     * @return 实例对象
     */
    @Override
    public PetDO update(PetDO pet) {
        this.petDao.update(pet);
        return this.queryById(pet.getPetId());
    }

    /**
     * 通过主键删除数据
     *
     * @param petId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer petId) {
        return this.petDao.deleteById(petId) > 0;
    }
}
