package microservices.api.config

import groovy.transform.CompileStatic
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@CompileStatic
@Configuration
class RestTemplateConfig {

    @LoadBalanced
    @Bean
    RestTemplate restTemplate() {
        new RestTemplate()
    }
}
