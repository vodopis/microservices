package microservices.api.productservice.config

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("shopback.api")
class ProductConfig {
    static void main(String[] args) {
        SpringApplication.run(ProductConfig.class, args)
    }
}
