package com.jxd.furn;

import com.jxd.furn.bean.Furn;
import com.jxd.furn.mapper.FurnMapper;
import com.jxd.furn.service.FurnService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class ApplicationTest {
    @Resource
    private FurnMapper furnMapper;

    @Resource
    private FurnService furnService;


    @Test
    public void testFurnMapper() {
        System.out.println("furnMapper--" + furnMapper.getClass());
        Furn furn = furnMapper.selectById(4);
        System.out.println("furn--" + furn);
    }

    @Test
    public void testFurnService() {
        List<Furn> furns = furnService.list();
        for (Furn furn : furns) {
            System.out.println("furn--" + furn);
        }
    }
}
