package com.leechi.amz.analytics.features.account.repo;

import com.leechi.amz.analytics.features.account.entity.UserEntity;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.Test;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepoTest {
    @Autowired
    private UserRepo userRepo;


    @Test
    public void simpleSaveTest(){
        UserEntity user = new UserEntity();
        user.setLogin("wlin");
        user.setFirstName("wenhao");
        user.setLastName("lin");
        user.setEmail("wenhao.lin@gmail.com");
        user.setPassword("P@ssword1");
        userRepo.save(user);

        UserEntity saved = userRepo.findOne(user.getId());
        assertNotNull(saved.getId());
        assertEquals(user.getLogin(), saved.getLogin());
    }
}
