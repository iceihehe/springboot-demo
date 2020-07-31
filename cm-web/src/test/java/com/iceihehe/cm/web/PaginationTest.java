package com.iceihehe.cm.web;

import com.iceihehe.cm.dao.entity.MTask;
import com.iceihehe.cm.dao.mapper.MTaskMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public class PaginationTest {
    @Resource
    MTaskMapper mapper;

    @Test
    public void testPagination() {
        List<MTask> result1 = mapper.getMTaskList(85, 0, 1);
        assert (result1.toArray().length == 1);
        List<MTask> result2 = mapper.getMTaskList(85, 0, 0);
        assert (result2.toArray().length > 1);
    }
}
