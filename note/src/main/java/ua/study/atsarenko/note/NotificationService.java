package ua.study.atsarenko.note;

import org.springframework.stereotype.Service;
import ua.study.atsarenko.clients.fraud.NotificationRequest;

import java.time.LocalDate;

@Service
public record NotificationService(NotificationRepository notificationRepository) {
    public void sendNotification(NotificationRequest notificationRequest) {
        notificationRepository.save(
                Notification.builder()
                        .toCustomerId(notificationRequest.toCustomerId())
                        .toCustomerEmail(notificationRequest.toCustomerEmail())
                        .sender("Anton Tsarenko")
                        .message(notificationRequest.message())
                        .sentAt(LocalDate.now())
                        .build()
        );
    }
}
