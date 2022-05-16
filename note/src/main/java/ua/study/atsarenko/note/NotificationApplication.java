package ua.study.atsarenko.note;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import ua.study.atsarenko.amqp.RabbitMQMessageProducer;

@SpringBootApplication(
        scanBasePackages = {
                "ua.study.atsarenko.note",
                "ua.study.atsarenko.amqp"
        }
)
@PropertySources({
        @PropertySource("classpath:clients-${spring.profile.active}.properties")
})
public class NotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

    /*@Bean
    CommandLineRunner commandLineRunner(
            RabbitMQMessageProducer producer,
            NotificationConfig config
    ) {
        return args -> {
            producer.publish(
                    new Person("Ali", 18),
                    config.getInternalExchange(),
                    config.getInternalNotificationRoutingKey()
            );
        };
    }*/
}
