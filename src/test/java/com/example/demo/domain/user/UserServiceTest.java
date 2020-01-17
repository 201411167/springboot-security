package com.example.demo.domain.user;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    UserService service;

    Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

    @After
    public void cleanUp(){
        service.deleteUser("test_id");
        service.deleteUser("test_id2");
    }

    @Test
    public void 유저_서비스_테스트() {
        // given
        UserEntity user1 = UserEntity.builder().id("test_id").email("test_email@gmail.com").password("test_password").authority("ROLE_ADMIN").build();
        UserEntity user2 = UserEntity.builder().id("test_id2").email("test_email2@gmail.com").password("test_password").authority("ROLE_MEMBER").build();
        service.addUser(user1);
        service.addUser(user2);
        logger.info("service.addUser() :" + "\t" + user1.toString());
        logger.info("service.addUser() :" + "\t" + user2.toString());

        // when
        UserDetails admin = service.loadUserByUsername("test_id");
        logger.info("service.loadUserByUsername() :" + "\t" + admin.toString());

        UserDetails member = service.loadUserByUsername("test_id2");
        logger.info("service.loadUserByUsername() :" + "\t" + member.toString());

        // then
        Collection<? extends GrantedAuthority> admin_authorities = admin.getAuthorities();
        Collection<? extends GrantedAuthority> member_authorities = member.getAuthorities();
        logger.info("admin.getAuthorities() :" + "\t" + admin_authorities.toString());
        logger.info("member.getAuthorities() :" + "\t" + member_authorities.toString());
        assertThat(admin_authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN")), is(true));
        assertThat(member_authorities.contains(new SimpleGrantedAuthority("ROLE_MEMBER")), is(true));
    }
}