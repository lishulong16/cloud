package cloud.consumeruser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@EnableAutoConfiguration
@EnableDiscoveryClient
@ComponentScan(basePackages = {"cloud.consumeruser.controller"})
public class ConsumerUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerUserApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }


}

