package com.example.demo.domain.user;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserEntityTest {

    Logger logger = LoggerFactory.getLogger(UserEntityTest.class);


    @Test
    public void 유저_엔티티_테스트(){
        UserEntity user1 = UserEntity.builder()
                .id("test_id")
                .email("test_email@gmail.com")
                .password("test_password")
                .authority("ROLE_ADMIN")
                .build();

        logger.info("UserEntity.builder() :" + "\t" + user1.toString());

        assertThat(user1.getAuthority(), is("ROLE_ADMIN"));
    }
}