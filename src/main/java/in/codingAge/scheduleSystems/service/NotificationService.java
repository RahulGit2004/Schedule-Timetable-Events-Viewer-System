package in.codingAge.scheduleSystems.service;

import in.codingAge.scheduleSystems.model.Notification;

import java.util.List;

public interface NotificationService {
    Notification saveUpdates(Notification notification);

    List<Notification> getNotificationsByUserId(String userId);

    Boolean markNotificationAsRead(String notificationId);
}
