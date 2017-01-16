package shopback.gateway.config

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.zuul.EnableZuulProxy
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer

@EnableZuulProxy
@SpringBootApplication
@ComponentScan("shopback.api.interceptor")
class ApiGateway extends WebSecurityConfigurerAdapter {

    @Configuration
    @EnableAuthorizationServer
    static class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
        @Override
        void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients.inMemory()
                    .withClient("test")
                    .secret("test123")
                    .authorizedGrantTypes("client_credentials")
                    .scopes("read,write")
        }
    }

    @Configuration
    @EnableResourceServer
    static class ResourceServerConfig extends ResourceServerConfigurerAdapter {

        static final String RESOURCE_ID = "shopback_api"

        @Override
        void configure(ResourceServerSecurityConfigurer resources) {
            resources.resourceId(RESOURCE_ID)
        }

        @Override
        void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests().antMatchers("/products/**").authenticated()
            http.authorizeRequests().antMatchers("/stores/**").authenticated()
        }
    }

    static void main(String[] args) {
        SpringApplication.run(ApiGateway.class, args)
    }
}
