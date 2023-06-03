package ltw.btl.config;

import lombok.RequiredArgsConstructor;
import ltw.btl.controller.filterRequest.AdminFilter;
import ltw.btl.service.auth.TokenProvider;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
@RequiredArgsConstructor
public class AppConfig {
    private final TokenProvider tokenProvider;
    @Bean
    public FilterRegistrationBean<AdminFilter> adminFilterRegistration() {
        FilterRegistrationBean<AdminFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new AdminFilter(tokenProvider));
        registration.addUrlPatterns("/*");
        registration.setName("CustomFilter");
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE); // Đặt order của filter là cao nhất
        return registration;
    }
}
