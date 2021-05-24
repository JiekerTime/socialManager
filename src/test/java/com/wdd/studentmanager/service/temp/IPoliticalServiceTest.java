package com.wdd.studentmanager.service.temp;

import com.jd.socialmanager.entity.PoliticalDO;
import com.jd.socialmanager.service.IPoliticalService;
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
public class IPoliticalServiceTest extends TestCase {
    @Autowired
    private IPoliticalService politicalService;

    @Test
    public void queryById() {
        System.out.println(politicalService.queryById(1));
    }

    @Test
    public void queryAll() {
        PoliticalDO political = new PoliticalDO();
        political.setCharge(50.0);
        final List<PoliticalDO> politicalDOS = politicalService.queryAll(political, 0, 1);
        for (PoliticalDO politicalDO : politicalDOS) {
            System.out.println(politicalDO);
        }
    }
}