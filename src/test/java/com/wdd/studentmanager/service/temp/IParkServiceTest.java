package com.wdd.studentmanager.service.temp;

import com.jd.socialmanager.entity.ParkDO;
import com.jd.socialmanager.service.IParkService;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author : Jieker
 * @Description :
 * @date : 2021/5/19 14:50
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IParkServiceTest extends TestCase {
    @Autowired
    private IParkService partService;

    @Test
    public void queryById() {
        System.out.println(partService.queryById(1));
    }

    @Test
    public void queryAll() {
        ParkDO part = new ParkDO();
        part.setParkId(1);
        System.out.println(partService.queryAll(part, 0, 1));
    }

}