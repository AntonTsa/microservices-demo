package ua.study.atsarenko.clients.note;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name= "note",
        url="${clients.note.url}"
)
public interface NotificationClient {
    @PostMapping("api/v1/notification")
    void sendNotification(NotificationRequest request);
}
