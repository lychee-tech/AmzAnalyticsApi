package com.lychee.amz.analytics.testhelp;



import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.codec.Base64;

import java.nio.charset.Charset;

public class HttpCredentialHelp {

    public static HttpHeaders createHttpBasicHeader(String username, String password){
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.encode(auth.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String( encodedAuth );

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);

        return headers;
    }

    public static HttpHeaders createHttpBearerHeader(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        return headers;
    }
}
