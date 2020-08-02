package softuni.emuseum.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import softuni.emuseum.services.api.PageStatService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class VisitsCounterInterceptor extends HandlerInterceptorAdapter {

    private final PageStatService pageStatService;

    public VisitsCounterInterceptor(PageStatService pageStatService) {
        this.pageStatService = pageStatService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String route;

        route = request.getRequestURI();

        this.pageStatService.updatePageCounter(route);

        return true;
    }
}
