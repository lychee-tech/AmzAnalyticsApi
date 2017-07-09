package com.lychee.amz.analytics.advice;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ErrorMessageAdviceTest {
    @Autowired
    private MessageAdvice errorMessageAdvice;

    @Test
    public void testErrorMessage(){
        assertEquals("Authentication error, make sure login name and password are correct.", errorMessageAdvice.authUserNotFound);
    }
}
