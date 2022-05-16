package ua.study.atsarenko.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication(
        scanBasePackages = {
                "ua.study.atsarenko.customer",
                "ua.study.atsarenko.amqp"
        }
)
@EnableEurekaClient
@EnableFeignClients(
        basePackages = "ua.study.atsarenko.clients"
)
@PropertySources({
        @PropertySource("classpath:clients-${spring.profile.active}.properties")
})
public class CustomerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }
}
