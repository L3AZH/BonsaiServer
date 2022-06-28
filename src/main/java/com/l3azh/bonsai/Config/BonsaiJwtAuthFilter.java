package com.l3azh.bonsai.Config;

import com.l3azh.bonsai.Service.UserDetailImplService;
import com.l3azh.bonsai.Util.AppUtils;
import com.l3azh.bonsai.Util.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class BonsaiJwtAuthFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtil;

    private final UserDetailImplService userDetailImplService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = getJwtFromHeaderRequest(request);
            if (jwt != null && jwtUtil.validateJwtToken(jwt)) {
                String email = jwtUtil.getEmailFromJwt(jwt);
                UserDetails userDetails = userDetailImplService.loadUserByUsername(email);
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            } else if (jwt != null && !jwtUtil.validateJwtToken(jwt)) {
                AppUtils.sendError(response, HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized");
                return;
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            log.error(String.format("Cannot set user authentication : \n %s", e.getMessage()));
            AppUtils.sendError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    String.format("Cannot set user authentication : \n %s", e.getMessage()));
        }
    }

    private String getJwtFromHeaderRequest(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if (!(headerAuth == null ||
                headerAuth.isBlank() ||
                headerAuth.isEmpty()) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7, headerAuth.length());
        }
        return null;
    }
}
