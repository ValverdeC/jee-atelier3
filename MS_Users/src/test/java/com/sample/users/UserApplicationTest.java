package com.sample.users;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApplication.class)
public class UserApplicationTest {

    @Test
    public void contextLoads() {
    }

    @Test
    public void testUserApplication()
    {
        UserApplication.main(new String[]{
                "--spring.main.web-environment=false",
        });
    }

}