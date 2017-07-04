package com.lychee.amz.analytics.features.account.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UpdateAccountRequestTest {
    @Test
    public void JsonIgnoreObjectIdTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        UpdateAccountRequest request = new UpdateAccountRequest();
        request.setId(1);
        request.setLogin("yaoyuannie");
        request.setFirstName("luyuan");
        request.setLastName("Nie");
        request.setEmail("nie.luyuan@gmail.com");
        request.setPassword("P@ssword1");
        request.setPhone("123456");
        String jsonInString = mapper.writeValueAsString(request);
        assertTrue(!jsonInString.contains("id"));
    }

    @Test
    public void JsonIgnoreStringIdTest() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String content = "{\"id\":1,\"email\":\"nie.luyua@gmail.com\",\"firstName\":\"luyuan\",\"lastName\":\"Nie\",\"login\":\"ss\",\"phone\":\"dfdfd\",\"password\":\"fdfdfd\"}";
        UpdateAccountRequest request = mapper.readValue(content,UpdateAccountRequest.class);
        assertEquals(request.getId(),null);
    }
}
