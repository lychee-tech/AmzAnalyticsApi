package com.handacc.amz.analytics.features.account.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.handacc.amz.analytics.features.account.AccountController;
import com.handacc.amz.analytics.features.account.dto.CreateAccountRequest;
import com.handacc.amz.analytics.features.account.dto.CreateAccountResponse;
import com.handacc.amz.analytics.features.account.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

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
        CreateAccountRequest request = new CreateAccountRequest();
        request.setPhone("5105899616");
        request.setEmail("nie.luyuan@gmail.com");
        request.setLogin("yaoyuannie");
        request.setFirstName("luyuan");
        request.setLastName("Nie");
        ObjectMapper mapper = new ObjectMapper();

        String content = "{'email':'nie.luyua@gmail.com','firstName':'luyuan','lastName':'Nie','login':'ss','phone':'dfdfd','password':'fdfdfd'}";
        mockMvc.perform(MockMvcRequestBuilders.post("/accounts").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    };

    @Test
    public void testInvalidEmail() throws Exception{
        CreateAccountRequest request = new CreateAccountRequest();
        request.setPhone("5105899616");
        request.setEmail("nie.luyuangmail.com");
        request.setLogin("yaoyuannie");
        request.setFirstName("luyuan");
        request.setLastName("Nie");
        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(MockMvcRequestBuilders.post("/accounts").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(request)))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().is(400));
    }


}
