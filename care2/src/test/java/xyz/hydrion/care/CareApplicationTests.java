package xyz.hydrion.care;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.hydrion.care.domain.ElderDevStatus;
import xyz.hydrion.care.domain.User;
import xyz.hydrion.care.mapper.ElderDevMapper;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CareApplicationTests {

    @Autowired
    private ElderDevMapper mapper;

    @Test
    public void contextLoads() {
        List<ElderDevStatus> list = mapper.selectStatusByDevId(2);
        for (ElderDevStatus s: list) {
            System.out.println(s.getHeartRate() );
        }
    }

}

