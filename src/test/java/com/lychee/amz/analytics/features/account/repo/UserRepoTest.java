package com.lychee.amz.analytics.features.account.repo;

import com.lychee.amz.analytics.features.account.entity.UserEntity;
import com.lychee.amz.analytics.features.account.service.AccountService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.Test;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserRepoTest {
    @Autowired
    private UserRepo userRepo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    @Test
    public void simpleSaveTest(){
        UserEntity user = new UserEntity();
        user.setEmail("wenhao1.lin@gmail.com");
        user.setEncryptedPassword(encoder.encode("P@ssword1"));
        userRepo.save(user);

        UserEntity saved = userRepo.findOne(user.getId());
        assertNotNull(saved.getId());
        assertEquals(user.getEmail(), saved.getEmail());

        assertEquals(user.getEncryptedPassword(), saved.getEncryptedPassword());
    }

    @Test
    public void findByEmailTest(){
        UserEntity user = new UserEntity();
        user.setEmail("wenhao2.lin@gmail.com");
        user.setEncryptedPassword(encoder.encode("P@ssword1"));
        userRepo.save(user);
        UserEntity saved = userRepo.findByEmail("wenhao2.lin@gmail.com");

        assertEquals(user.getId(),saved.getId());
    }

    @Test
    public void findByIdTest(){
        UserEntity user = new UserEntity();
        user.setEmail("wenhao3.lin@gmail.com");
        user.setEncryptedPassword(encoder.encode("P@ssword1"));
        userRepo.save(user);

        UserEntity saved = userRepo.findById(user.getId());
        assertEquals(saved.getEmail(),user.getEmail());
    }



}
