package com.handacc.amz.analytics.features.account.repo;

import com.handacc.amz.analytics.features.account.AccountController;
import com.handacc.amz.analytics.features.account.dto.CreateAccountRequest;
import com.handacc.amz.analytics.features.account.dto.CreateAccountResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class AccountControllerTest {
    @Autowired
    AccountController accountController;

    @Autowired
    private WebApplicationContext webContext;

    private MockMvc mockMvc;

    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
    }

    @Test
    public void createAccountTest(){
        CreateAccountRequest accountRequest = new CreateAccountRequest();
        accountRequest.setLogin("yaoyuannie");
        accountRequest.setFirstName("luyuan");
        accountRequest.setLastName("Nie");
        accountRequest.setEmail("nie.luyuan@gmail.com");
        accountRequest.setPassword("123456");
        accountRequest.setPhone("5103005484");
        CreateAccountResponse createAccountResponse = accountController.createAccount(accountRequest);

        assertEquals(accountRequest.getEmail(), createAccountResponse.getEmail());
    }

    @Test
    public void testCreateAccountPost() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/accounts")
                .param("email","nie.luyuan@gmail.com")
                .param("firstName","luyuan")
                .param("lastName","Nie")
                .param("login","yaoyuannie")
                .param("phone","4105899616")
                .param("password","123456"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }



}
