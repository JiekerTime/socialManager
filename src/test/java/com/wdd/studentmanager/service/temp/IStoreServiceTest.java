package com.wdd.studentmanager.service.temp;

import com.jd.socialmanager.SocialManagerApplication;
import com.jd.socialmanager.entity.StoreDO;
import com.jd.socialmanager.service.IStoreService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author : Jieker
 * @Description :
 * @date : 2021/5/19 14:51
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SocialManagerApplication.class)
public class IStoreServiceTest {
    @Autowired
    private IStoreService storeService;

    @Test
    public void queryById() {
        System.out.println(storeService.queryById(1));
    }

    @Test
    public void queryAll() {
        StoreDO store = new StoreDO();
        store.setStoreName("文具");
        final List<StoreDO> storeDOS = storeService.queryAll(store, 0, 1);
        for (StoreDO storeDO : storeDOS) {
            System.out.println(storeDO);
        }
    }
}