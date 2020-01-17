package com.example.demo.domain.user;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    UserRepository repo;

    Logger logger = LoggerFactory.getLogger(UserRepositoryTest.class);

    @After
    public void cleanUp() {
        Optional<UserEntity> user = repo.findById("test_id");
        user.ifPresent(userEntity -> repo.delete(userEntity));
    }

    @Test
    public void 유저_레포지토리_테스트() {
        // given
        repo.save(UserEntity.builder()
                .id("test_id")
                .email("test_email@gmail.com")
                .password("test_password")
                .build());

        // when
        List<UserEntity> userList = repo.findAll();
        UserEntity userById = repo.findById("test_id").get();
        List<UserEntity> userListByEmail = repo.findByEmail("test_email@gmail.com");

        logger.info("repo.findAll() :" + "\t" + userList.toString());
        logger.info("repo.findById() :" + "\t" + userById.toString());
        logger.info("repo.findByEmail() " + "\t" + userListByEmail.toString());


        // then
        assertThat(userList.isEmpty(), is(false));
        assertThat(userById.getId(), is("test_id"));
        assertThat(userListByEmail.isEmpty(), is(false));
    }
}