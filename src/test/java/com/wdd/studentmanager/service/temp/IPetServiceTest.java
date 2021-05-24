package com.wdd.studentmanager.service.temp;

import com.jd.socialmanager.entity.PetDO;
import com.jd.socialmanager.service.IPetService;
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
public class IPetServiceTest extends TestCase {
    @Autowired
    private IPetService petService;

    @Test
    public void queryById() {
        System.out.println(petService.queryById(1));
    }

    @Test
    public void queryAll() {
        PetDO pet = new PetDO();
        pet.setPetType("狗狗");
        final List<PetDO> petDOS = petService.queryAll(pet, 0, 1);
        for (PetDO petDO : petDOS) {
            System.out.println(petDO);
        }
    }

}