package com.wdd.studentmanager.service.temp;

import com.jd.socialmanager.entity.PropertyDO;
import com.jd.socialmanager.service.IPropertyService;
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
public class IPropertyServiceTest extends TestCase {
    @Autowired
    private IPropertyService propertyService;

    @Test
    public void queryById() {
        System.out.println(propertyService.queryById(1));
    }

    @Test
    public void queryAll() {
        PropertyDO property = new PropertyDO();
        property.setPropertyName("联华");
        final List<PropertyDO> propertyDOS = propertyService.queryAll(property, 0, 1);
        for (PropertyDO propertyDO : propertyDOS) {
            System.out.println(propertyDO);
        }
    }
}