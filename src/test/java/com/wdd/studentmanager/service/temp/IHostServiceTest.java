package com.wdd.studentmanager.service.temp;

import com.jd.socialmanager.entity.HostDO;
import com.jd.socialmanager.service.IHostService;
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
public class IHostServiceTest extends TestCase {
    @Autowired
    private IHostService hostService;

    @Test
    public void queryById() {
        System.out.println(hostService.queryById(1));
    }

    @Test
    public void queryAll() {
        HostDO host = new HostDO();
        host.setHostName("志");
        final List<HostDO> hostDOS = hostService.queryAll(host, 0, 1);
        for (HostDO hostDO : hostDOS) {
            System.out.println(hostDO);
        }
    }

}