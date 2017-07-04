package com.lychee.amz.analytics.features.account.repo;

import com.lychee.amz.analytics.features.account.entity.UserEntity;
import com.lychee.amz.analytics.features.account.service.AccountService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

    @Autowired
    private AccountService accountService;

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

    @Test
    public void findByEmailTest(){
        UserEntity user = new UserEntity();
        user.setLogin("wlin");
        user.setFirstName("wenhao");
        user.setLastName("lin");
        user.setEmail("wenhao.lin@gmail.com");
        user.setPassword("P@ssword1");
        userRepo.save(user);
        UserEntity saved = userRepo.findByEmail("wenhao.lin@gmail.com");

        assertEquals(user.getId(),saved.getId());
    }

    @Test
    public void findByIdTest(){
        UserEntity user = new UserEntity();
        user.setLogin("wlin");
        user.setFirstName("wenhao");
        user.setLastName("lin");
        user.setEmail("wenhao.lin@gmail.com");
        user.setPassword("P@ssword1");
        userRepo.save(user);

        UserEntity saved = userRepo.findById(user.getId());
        assertEquals(saved.getEmail(),user.getEmail());
    }

    @Test
    public void existsByEmailTest(){
        UserEntity user = new UserEntity();
        user.setLogin("wlin");
        user.setFirstName("wenhao");
        user.setLastName("lin");
        user.setEmail("wenhao.lin@gmail.com");
        user.setPassword("P@ssword1");
        userRepo.save(user);
        boolean isExist = userRepo.existsByEmail("wenhao.lin@gmail.com");
        assertEquals(true,isExist);
    }

    @Test
    public void findByLoginTest(){
        UserEntity user = new UserEntity();
        user.setLogin("wlin");
        user.setFirstName("wenhao");
        user.setLastName("lin");
        user.setEmail("wenhao.lin@gmail.com");
        user.setPassword("P@ssword1");
        userRepo.save(user);
        UserEntity saved = userRepo.findByLogin("wlin");
        assertEquals(user.getLogin(),saved.getLogin());
    }
}
