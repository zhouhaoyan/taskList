package person.zhy.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorsConfig extends WebMvcConfigurerAdapter {


    @Bean
    public TokenInterceptor tokenInterceptor() {
        return new TokenInterceptor();
    }
    @Bean
    public LogInInterceptor logInInterceptor() {
        return new LogInInterceptor();
    }

    @Autowired
    TokenInterceptor tokenInterceptor;
    @Autowired
    LogInInterceptor logInInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/login/**");

    }
}
