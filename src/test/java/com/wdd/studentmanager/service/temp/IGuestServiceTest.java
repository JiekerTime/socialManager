package com.wdd.studentmanager.service.temp;

import com.jd.socialmanager.entity.GuestDO;
import com.jd.socialmanager.service.IGuestService;
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
public class IGuestServiceTest extends TestCase {
    @Autowired
    private IGuestService guestService;

    @Test
    public void queryById() {
        System.out.println(guestService.queryById(1));
    }

    @Test
    public void queryAll() {
        GuestDO guest = new GuestDO();
        guest.setGuestName("四");
        final List<GuestDO> guestDOS = guestService.queryAll(guest, 0, 1);
        for (GuestDO guestDO : guestDOS) {
            System.out.println(guestDO);
        }
    }
}