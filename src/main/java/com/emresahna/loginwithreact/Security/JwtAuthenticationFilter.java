package com.emresahna.loginwithreact.Security;

import com.emresahna.loginwithreact.Dto.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        LoginRequest authCredentals;
        try {
            authCredentals = new ObjectMapper().readValue(request.getReader(), LoginRequest.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        UsernamePasswordAuthenticationToken usernamePAT = new UsernamePasswordAuthenticationToken(
            authCredentals.getUsername(),
            authCredentals.getPassword(),
                Collections.emptyList()
        );

        return getAuthenticationManager().authenticate(usernamePAT);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException{
        UserDetailImpl userDetail = (UserDetailImpl) authResult.getPrincipal();
        String token = TokenUtils.createToken(userDetail.getId(),userDetail.getUsername());
        response.setHeader("Access-Control-Expose-Headers","Authorization");
        response.addHeader("Authorization","Bearer "+token);
        response.getWriter().flush();
    }
}
