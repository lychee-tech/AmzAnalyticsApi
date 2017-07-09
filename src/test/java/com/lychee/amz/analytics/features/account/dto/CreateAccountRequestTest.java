package com.lychee.amz.analytics.features.account.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CreateAccountRequestTest {
    @Test
    public void JsonIgnoreObjectIdTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        CreateAccountRequest request = new CreateAccountRequest();
        request.setEmail("nie.luyuan@gmail.com");
        request.setPassword("P@ssword1");
        String json = mapper.writeValueAsString(request);
        String expected = "{\"email\":\"nie.luyuan@gmail.com\",\"password\":\"P@ssword1\"}";
        assertEquals(expected, json);
    }

    @Test
    public void JsonIgnoreStringIdTest() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String json ="{\"email\":\"nie.luyuan@gmail.com\",\"password\":\"P@ssword1\"}";
        CreateAccountRequest request = mapper.readValue(json,CreateAccountRequest.class);
        assertEquals("nie.luyuan@gmail.com", request.getEmail());
        assertEquals("P@ssword1", request.getPassword());
    }
}
