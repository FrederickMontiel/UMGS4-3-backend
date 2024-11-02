package com.miumg.fmontiel.eventos.eventos.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.handler.WebRequestHandlerInterceptorAdapter;

import com.miumg.fmontiel.eventos.eventos.exceptions.AccesDeniedException;
import com.miumg.fmontiel.eventos.eventos.helpers.JsonWebToken;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthenticatorInterceptor extends WebRequestHandlerInterceptorAdapter {
    public AuthenticatorInterceptor(WebRequestInterceptor requestInterceptor) {
        super(requestInterceptor);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String token = request.getHeader("Authorization");

        if (token == null) {
            throw new AccesDeniedException();
        }

        String[] parts = token.split(" ");

        if (parts[1] == null) {
            throw new AccesDeniedException();
        }

        if (parts[0] != "Bearer") {
            throw new AccesDeniedException();
        }

        try {
            JsonWebToken.decode(parts[1]);
        } catch (Exception e) {
            throw new AccesDeniedException();
        }

        return true;
    }

}
