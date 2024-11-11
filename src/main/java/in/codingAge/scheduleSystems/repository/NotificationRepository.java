package in.codingAge.scheduleSystems.repository;

import in.codingAge.scheduleSystems.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends MongoRepository<Notification,String> {
    List<Notification> findAllByUserId(String userId);

    Notification findByNotificationId(String notificationId);
}
