package ua.study.atsarenko.customer;

import org.springframework.stereotype.Service;
import ua.study.atsarenko.clients.fraud.FraudCheckResponse;
import ua.study.atsarenko.clients.fraud.FraudClient;
import ua.study.atsarenko.clients.fraud.NotificationClient;
import ua.study.atsarenko.clients.fraud.NotificationRequest;

@Service
public record CustomerService(
        CustomerRepository customerRepository,
        FraudClient fraudClient,
        NotificationClient notificationClient
) {

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

        notificationClient.sendNotification(
                new NotificationRequest(
                        customer.getId(),
                        customer.getEmail(),
                        String.format("Welcome, %s", customer.getFirstName())
                )
        );
    }
}
