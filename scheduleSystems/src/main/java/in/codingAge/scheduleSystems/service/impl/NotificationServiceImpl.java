package in.codingAge.scheduleSystems.service.impl;

import in.codingAge.scheduleSystems.exception.AppException;
import in.codingAge.scheduleSystems.model.Notification;
import in.codingAge.scheduleSystems.model.User;
import in.codingAge.scheduleSystems.repository.NotificationRepository;
import in.codingAge.scheduleSystems.service.NotificationService;
import in.codingAge.scheduleSystems.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private UserService userService;


    @Autowired
    private NotificationRepository notificationRepository;
    @Override
    public Notification saveUpdates(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getNotificationsByUserId(String userId) {
        User user = userService.getUserByUserId(userId);
        if (user == null) {
            throw new AppException("Invalid User");
        } else {
            return notificationRepository.findAllByUserId(userId);
        }
    }

    @Override
    public Boolean markNotificationAsRead(String notificationId) {
        Notification notification = notificationRepository.findByNotificationId(notificationId);
        if (notification == null) {
            throw new AppException("Invalid id");
        } else {
            notification.setReadNotification(true);
            notificationRepository.save(notification);
            return true;
        }
    }
}
