package com.lychee.amz.analytics.features.authentication.filter;


import com.lychee.amz.analytics.Exception.ApiErrorHelp;
import com.lychee.amz.analytics.Exception.ApiErrorResponse;
import com.lychee.amz.analytics.features.account.model.Roles;
import com.lychee.amz.analytics.features.authentication.exception.AuthUserNotFoundException;
import com.lychee.amz.analytics.features.authentication.exception.BadCredentialException;
import com.lychee.amz.analytics.features.authentication.model.AuthUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


public class JwtTokenFilter extends AbstractAuthenticationProcessingFilter {
    public JwtTokenFilter(String apiUrl, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(apiUrl));
        super.setAuthenticationManager(authManager);
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String uri = StringUtils.defaultString(request.getRequestURI(), "");
        String method = StringUtils.defaultString(request.getMethod(), "");

        UsernamePasswordAuthenticationToken authentication;

        if ("/api/accounts".equalsIgnoreCase(uri.trim()) && "post".equalsIgnoreCase(method.trim())) {
            //assume anonymous user
            authentication = createAnonymousUser();
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return authentication;
        }


        //parse from jwt token
        try {
            authentication = HttpCredentialParser.parseJwtToken(request);
        } catch (Exception ex) {
            throw new BadCredentialException();
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response, AuthenticationException failed) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.toString());

        ApiErrorResponse errorResponse = new ApiErrorResponse("AuthenticationError", failed.getMessage());
        response.getWriter().println(ApiErrorHelp.toJson(errorResponse));
        response.getWriter().flush();
    }

    @Override
    protected void successfulAuthentication(
            final HttpServletRequest request, final HttpServletResponse response,
            final FilterChain chain, final Authentication authResult)
            throws IOException, ServletException {

        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken createAnonymousUser() {
        AuthUser authUser = new AuthUser("anonymous", "NA", AuthorityUtils.createAuthorityList(Roles.anonymous));
        authUser.setDisplayName("guest");
        authUser.setId(-1);
        return new UsernamePasswordAuthenticationToken(authUser, authUser.getPassword(), authUser.getAuthorities());
    }
}
