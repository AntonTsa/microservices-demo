package ua.study.atsarenko.note;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository
        extends JpaRepository<Notification, Integer> {
}
