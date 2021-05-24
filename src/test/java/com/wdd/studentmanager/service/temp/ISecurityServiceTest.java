package com.wdd.studentmanager.service.temp;

import com.jd.socialmanager.entity.SecurityDO;
import com.jd.socialmanager.service.ISecurityService;
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
public class ISecurityServiceTest extends TestCase {
    @Autowired
    private ISecurityService securityService;

    @Test
    public void queryById() {
        System.out.println(securityService.queryById(1));
        ;
    }

    @Test
    public void queryAll() {
        SecurityDO security = new SecurityDO();
        security.setSecurityName("李");
        final List<SecurityDO> securityDOS = securityService.queryAll(security, 0, 1);
        for (SecurityDO securityDO : securityDOS) {
            System.out.println(securityDO);
        }
    }
}