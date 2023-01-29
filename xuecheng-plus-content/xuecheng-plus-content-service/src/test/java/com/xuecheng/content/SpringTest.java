package com.xuecheng.content;


import com.xuecheng.content.mapper.CourseBaseMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SpringTest {


    @Autowired
    private CourseBaseMapper courseBaseMapper;
    @Test
    public void query(){
        System.out.println(courseBaseMapper.selectList(null));
    }
}
