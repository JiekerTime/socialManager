package com.wdd.studentmanager.service.temp;

import com.jd.socialmanager.entity.BlockDO;
import com.jd.socialmanager.service.IBlockService;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author : Jieker
 * @Description :
 * @date : 2021/5/19 14:50
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IBlockServiceTest extends TestCase {
    @Autowired
    private IBlockService blockService;

    @Test
    public void queryById() {
        System.out.println(blockService.queryById(1));
    }

    @Test
    public void queryAll() {
        BlockDO block = new BlockDO();
        block.setBlockName("明");
        final List<BlockDO> blockDOS = blockService.queryAll(block, 0, 1);
        for (BlockDO blockDO : blockDOS) {
            System.out.println(blockDO);
        }
    }
}