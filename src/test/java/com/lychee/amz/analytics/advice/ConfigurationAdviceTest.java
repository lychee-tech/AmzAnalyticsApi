package com.lychee.amz.analytics.advice;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.Test;
import static  org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConfigurationAdviceTest {
    @Test
    public void testConfiguration(){
        assertEquals(1440, (int) ConfigurationAdvice.singleton.jwtExpirationInMinutes);
        assertEquals("www.lychee.com.signing_secret", ConfigurationAdvice.singleton.jwtSigningSecret);
    }

}
