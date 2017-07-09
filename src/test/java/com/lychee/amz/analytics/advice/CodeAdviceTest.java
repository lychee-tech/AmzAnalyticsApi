package com.lychee.amz.analytics.advice;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.Test;
import static  org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CodeAdviceTest {
    @Autowired
    CodeAdvice codeAdvice;
    @Test
    public void testErrorCodes(){
        assertEquals("InvalidInput", codeAdvice.invalidInput);
        assertEquals("AuthenticationError", codeAdvice.authenticationError);
    }
}
