package softuni.emuseum.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import softuni.emuseum.interceptors.UserActivityInterceptor;
import softuni.emuseum.interceptors.VisitsCounterInterceptor;

@Configuration
public class ApplicationWebMvcConfiguration implements WebMvcConfigurer {

    private final UserActivityInterceptor userActivityInterceptor;
    private final VisitsCounterInterceptor visitsCounterInterceptor;

    @Autowired
    public ApplicationWebMvcConfiguration(UserActivityInterceptor userActivityInterceptor, VisitsCounterInterceptor visitsCounterInterceptor) {
        this.userActivityInterceptor = userActivityInterceptor;
        this.visitsCounterInterceptor = visitsCounterInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.userActivityInterceptor);
        registry.addInterceptor(this.visitsCounterInterceptor);
    }

}
