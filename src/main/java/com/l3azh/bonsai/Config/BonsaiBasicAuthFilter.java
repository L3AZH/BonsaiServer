package com.l3azh.bonsai.Config;

import com.l3azh.bonsai.Util.AppUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class BonsaiBasicAuthFilter extends OncePerRequestFilter {

    private final PasswordEncoder passwordEncoder;

    @Value("${basic.auth.name}")
    private String username;

    @Value("${basic.auth.password}")
    private String password;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            Map<String, String> basicAccount = getUsernameAndPasswordFromRequest(request);
            if(basicAccount != null && !basicAccount.isEmpty()){
                if(username.equals(basicAccount.get("username")) && password.equals(basicAccount.get("password"))){
                    UserDetails userDetails = User
                            .withUsername(basicAccount.get("username"))
                            .password(passwordEncoder.encode(basicAccount.get("password")))
                            .roles(UserDetailImpl.BonsaiAccRole.USER.getValue())
                            .build();
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                } else {
                    AppUtils.sendError(response, HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized");
                    return;
                }
            }
            filterChain.doFilter(request, response);
        } catch (Exception e){
            log.error(String.format("Cannot set user authentication : \n %s", e.getMessage()));
            AppUtils.sendError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    String.format("Cannot set user authentication : \n %s", e.getMessage()));
        }
    }

    private Map<String, String> getUsernameAndPasswordFromRequest(HttpServletRequest request){
        String headerAuth = request.getHeader("Authorization");
        if (!(headerAuth == null ||
                headerAuth.isBlank() ||
                headerAuth.isEmpty()) && headerAuth.startsWith("Basic ")) {
            String base64String = headerAuth.substring(6, headerAuth.length());
            String base64DecodeString = new String(Base64.getDecoder().decode(base64String), StandardCharsets.UTF_8);
            Map<String, String> info = new HashMap<>();
            String[] data = base64DecodeString.split(":");
            info.put("username", data[0]);
            info.put("password", data[1]);
            return info;
        }
        return null;
    }
}
