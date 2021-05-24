package com.wdd.studentmanager.service.temp;

import com.jd.socialmanager.entity.RentDO;
import com.jd.socialmanager.service.IRentService;
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
public class IRentServiceTest extends TestCase {
    @Autowired
    private IRentService rentService;

    @Test
    public void queryById() {
        System.out.println(rentService.queryById(1));
    }

    @Test
    public void queryAll() {
        RentDO rent = new RentDO();
        rent.setRentName("一");
        final List<RentDO> rentDOS = rentService.queryAll(rent, 0, 1);
        for (RentDO rentDO : rentDOS) {
            System.out.println(rentDO);
        }
    }
}