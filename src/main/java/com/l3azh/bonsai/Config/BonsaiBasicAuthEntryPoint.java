package com.l3azh.bonsai.Config;

import com.l3azh.bonsai.Util.AppUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class BonsaiBasicAuthEntryPoint extends BasicAuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        log.error(String.format(String.format("Unauthorized Error {} \n %s", authException.getMessage())));
        AppUtils.sendError(response, HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized");
    }

    @Override
    public void afterPropertiesSet() {
        setRealmName("L3azh.Bonsai");
        super.afterPropertiesSet();
    }
}
