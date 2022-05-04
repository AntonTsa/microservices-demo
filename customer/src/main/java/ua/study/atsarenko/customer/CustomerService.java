package ua.study.atsarenko.customer;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ua.study.atsarenko.clients.fraud.FraudCheckResponse;
import ua.study.atsarenko.clients.fraud.FraudClient;

@Service
public record CustomerService(CustomerRepository customerRepository, RestTemplate restTemplate, FraudClient fraudClient) {

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        //todo: check if email valid
        //todo: check if email not taken
        customerRepository.saveAndFlush(customer);

        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if (fraudCheckResponse.isFraudster()){
            throw new IllegalStateException("fraudster");
        }

        //todo: send a notification
    }
}
