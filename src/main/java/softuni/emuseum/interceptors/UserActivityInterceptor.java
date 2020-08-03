package softuni.emuseum.interceptors;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import softuni.emuseum.services.api.UserActivityStatService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class UserActivityInterceptor extends HandlerInterceptorAdapter {

    private final UserActivityStatService userActivityStatService;

    public UserActivityInterceptor(UserActivityStatService userActivityStatService) {
        this.userActivityStatService = userActivityStatService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String username;
        String route;

        username= SecurityContextHolder.getContext().getAuthentication().getName();
        route = request.getRequestURI();

        this.userActivityStatService.saveActivity(username, route);

        return true;
    }

//----------------------------------------------------------------------------------------------------------------------

}
