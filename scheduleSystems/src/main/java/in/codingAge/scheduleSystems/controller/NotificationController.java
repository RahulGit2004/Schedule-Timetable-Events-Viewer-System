package in.codingAge.scheduleSystems.controller;

import in.codingAge.scheduleSystems.base.ApiResponse;
import in.codingAge.scheduleSystems.model.Notification;
import in.codingAge.scheduleSystems.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notification")
@CrossOrigin("*")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/user")
    public ApiResponse<List<Notification>> getNotificationsByUserId(@RequestParam String userId) {
        return new ApiResponse<>(notificationService.getNotificationsByUserId(userId), HttpStatus.ACCEPTED);
    }

    @PutMapping("/mark/read")
    public ApiResponse<Boolean> markNotificationAsRead(@RequestParam String notificationId) {
        return new ApiResponse<>(notificationService.markNotificationAsRead(notificationId), HttpStatus.ACCEPTED);
    }


}
