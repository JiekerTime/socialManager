package com.wdd.studentmanager.service.temp;

import com.jd.socialmanager.entity.Admin;
import com.jd.socialmanager.service.IAdminService;
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
 * @date : 2021/5/19 14:49
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IAdminServiceTest extends TestCase {
    @Autowired
    private IAdminService adminService;

    @Test
    public void findAdmin() {
        Admin admin = new Admin();
        admin.setUsername("admin");
        admin.setPassword("123456");
        System.out.println(adminService.findAdmin(admin));
    }

    @Test
    public void queryAll() {
        final List<Admin> admins = adminService.queryAll(null, 0, 1);
        for (Admin admin : admins) {
            System.out.println(admin);
        }
    }
}