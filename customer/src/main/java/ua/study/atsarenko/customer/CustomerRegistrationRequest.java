package ua.study.atsarenko.customer;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email) {

}
