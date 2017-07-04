package com.lychee.amz.analytics.features.auth.config;


import com.lychee.amz.analytics.Exception.ApiErrorHelp;
import com.lychee.amz.analytics.Exception.ApiErrorResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class LycheeAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        ApiErrorResponse errorResponse = new ApiErrorResponse("AuthenticationError", authException.getMessage());
        response.getWriter().println(ApiErrorHelp.toJson(errorResponse));
        response.getWriter().flush();
    }
}
