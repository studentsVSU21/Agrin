package ru.vsu.cs.Authentication;

import com.google.common.net.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;


public class TokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final String BEARER = "Bearer ";
    private final Predicate<String> matchBearerLength = authValue -> authValue.length() > BEARER.length();
    private final Function<String, String> isolateBearerValue = authValue -> authValue.substring(BEARER.length());

    public TokenAuthenticationFilter() {
        super("/**");
        setAuthenticationFailureHandler((request, response, authenticationException) -> {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setHeader("Error", authenticationException.getMessage());
            response.setHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Error");
        });
    }

    protected TokenAuthenticationFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authResult);
        chain.doFilter(request, response);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        String token = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        if (Objects.isNull(token) || !matchBearerLength.test(token)) {
            throw new AuthenticationException("Not Token") {
            };
        }
        token = isolateBearerValue.apply(token);
        TokenAuthentication tokenAuthentication = new TokenAuthentication(token);
        tokenAuthentication.setAuthenticated(true);
        return getAuthenticationManager().authenticate(tokenAuthentication);
    }
}