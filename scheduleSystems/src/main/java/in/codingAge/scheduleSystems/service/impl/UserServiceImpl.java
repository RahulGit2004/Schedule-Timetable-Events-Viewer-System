package in.codingAge.scheduleSystems.service.impl;

import in.codingAge.scheduleSystems.exception.AppException;
import in.codingAge.scheduleSystems.model.Notification;
import in.codingAge.scheduleSystems.model.User;
import in.codingAge.scheduleSystems.model.request.SignUpRequest;
import in.codingAge.scheduleSystems.model.response.LoginResponse;
import in.codingAge.scheduleSystems.repository.UserRepository;
import in.codingAge.scheduleSystems.service.NotificationService;
import in.codingAge.scheduleSystems.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Lazy
    private NotificationService notificationService;


    @Override
    public Boolean signUp(SignUpRequest signUpRequest) {
        // for unique phone number
        if(getUserByPhoneNumber(signUpRequest.getPhoneNumber()) != null) {
            throw new AppException("Phone number Already Exists");
        } else {
            User user = new User();
            user.setUserName(signUpRequest.getUserName());
            user.setPassword(signUpRequest.getPassword());
            user.setEmailId(signUpRequest.getEmailId());
            user.setPhoneNumber(signUpRequest.getPhoneNumber());
            user.setUserRole(signUpRequest.getUserRole());
            userRepository.save(user);
            return true;
        }
    }

    @Override
    public User getUserByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public LoginResponse singIn(String phoneNumber, String password) {
        // check correct phone number or not
        User user = getUserByPhoneNumber(phoneNumber);

        if(user != null) {
            // check correct password or not
            if(user.getPassword().equals(password)) {
                LoginResponse loginResponse = new LoginResponse();
                loginResponse.setLoggedId(user.getUserId());
                loginResponse.setLoggedRole(user.getUserRole());
                loginResponse.setLoggedUserName(user.getUserName());
                return loginResponse;
            } else {
                throw new AppException("Wrong Password");
            }
        } else {
            throw new AppException("Invalid Phone number");
        }
    }

    @Override
    public User getUserByUserId(String creatorId) {
        return userRepository.findByUserId(creatorId);
    }

    // this is for saving new updates on my database;
    @Override
    public User saveUpdates(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsersByRole(String userRole) {
        return userRepository.findAllByUserRole(userRole);
    }

    @Override
    public List<User> getAllStudents() {
        return getUsersByRole("Student");
    }

    @Override
    public List<Notification> getAllNotificationByUserId(String userId) {
        User user = getUserByUserId(userId);
        if (user == null) {
            throw new AppException("Invalid User");
        } else {
            return new ArrayList<>(user.getNotificationList());
        }
    }

    @Override
    public Boolean markNotificationRead(String userId, String notificationId) {
        User user = getUserByUserId(userId);
        if (user == null) {
            throw new AppException("Invalid User");
        } else {
            for (Notification notification: user.getNotificationList()) {
                if (notification.getNotificationId().equals(notificationId)) {
                    notification.setReadNotification(true);
                    notificationService.saveUpdates(notification);
                    userRepository.save(user);
                    return true;
                }
            }
            throw new AppException("Invalid Notification Id");
        }
    }
}
