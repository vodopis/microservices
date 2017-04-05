package microservices.api.config

import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import microservices.api.interceptor.LoggingInterceptor

@CompileStatic
@Configuration
class LoggingConfig extends WebMvcConfigurerAdapter {
    @Autowired
    LoggingInterceptor loggingInterceptor

    @Override
    void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingInterceptor);
    }
}
