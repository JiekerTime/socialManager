package com.wdd.studentmanager.service.temp;

import com.jd.socialmanager.entity.BaseDO;
import com.jd.socialmanager.service.IBaseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author : Jieker
 * @Description :
 * @date : 2021/5/19 14:19
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IBaseServiceTest {
    @Autowired
    private IBaseService baseService;

    @Test
    public void queryById() {
        System.out.println(baseService.queryById(1));
    }

    @Test
    public void queryAll() {
        BaseDO baseDO = new BaseDO();
        baseDO.setBaseName("绿");
        final List<BaseDO> baseDOS = baseService.queryAll(baseDO, 0, 1);
        for (BaseDO base : baseDOS) {
            System.out.println(base);
        }
    }
}