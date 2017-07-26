package com.ysg.security;

import com.ysg.model.UserScope;
import com.ysg.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by Thaslim on 22/03/17.
 */
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handlerMethod) throws Exception {

        if (!(handlerMethod instanceof HandlerMethod))
            return super.preHandle(request, response, handlerMethod);

        HandlerMethod handler = (HandlerMethod) handlerMethod;
        Method method = handler.getMethod();

        if (!method.isAnnotationPresent(Secured.class))
            return super.preHandle(request, response, handlerMethod);

        String auth = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (auth == null || auth.trim().length() == 0) {
            logger.error("AUTH header not received");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }

        UserScope user = tokenService.verify(auth);
        if (user == null) {
            logger.error("Error in verifying token");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }

        Secured secured = method.getAnnotation(Secured.class);
        if (secured.scopes().length == 0) {
            request.setAttribute(Constants.USER, user.getUser());
            request.setAttribute(Constants.ROLE, user.getRoles());
            return true;
        }

        for (String role : user.getRoles()) {
            Optional<String> found = Stream.of(secured.scopes()).filter(s -> s.equals(role)).findFirst();
            if (found.isPresent()) {
                request.setAttribute(Constants.USER, user.getUser());
                request.setAttribute(Constants.ROLE, user.getRoles());
                return true;
            }
        }

        logger.error("Unauthorized. Scope mismatch");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        return false;
    }
}
