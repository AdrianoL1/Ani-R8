package com.adrianoL.config.security;

import com.nimbusds.jwt.proc.BadJWTException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {

        Throwable cause = authException.getCause();
        response.setContentType("application/json");

        if(cause instanceof BadJWTException){
            response.getWriter().write("{\"error\": \"Invalid or expired token!\"}");
        }
        else {
            response.getWriter().write("{\"error\": \" " + cause.getMessage() +  "\"}");
        }

        response.setStatus(401);
    }
}
