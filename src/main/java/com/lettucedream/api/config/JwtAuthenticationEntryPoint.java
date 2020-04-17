package com.lettucedream.api.config;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
/**************************************************************************
 * @Author: Rohit Saidugari
 * Description:  classes are considered as candidates for auto-detection when
 * using annotation-based configuration and classpath scanning
 * NOTES: ref https://jwt.io/introduction/ for more info on JWT
 * REVISION HISTORY : None
 * Date:                           By: Rohit Saidugari          Description:
 ***************************************************************************/
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = -7858869558953243875L;


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
}

