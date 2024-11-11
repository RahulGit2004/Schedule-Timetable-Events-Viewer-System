package in.codingAge.scheduleSystems.controller;

import in.codingAge.scheduleSystems.base.ApiResponse;
import in.codingAge.scheduleSystems.model.Notification;
import in.codingAge.scheduleSystems.model.User;
import in.codingAge.scheduleSystems.model.request.SignUpRequest;
import in.codingAge.scheduleSystems.model.response.LoginResponse;
import in.codingAge.scheduleSystems.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ApiResponse<Boolean> signUp(@RequestBody SignUpRequest signUpRequest) {
        return new ApiResponse<>(userService.signUp(signUpRequest), HttpStatus.ACCEPTED);
    }

    @PostMapping("/singIn")
    public ApiResponse<LoginResponse> signIn(@RequestParam String phoneNumber, String password) {
        return new ApiResponse<>(userService.singIn(phoneNumber,password),HttpStatus.ACCEPTED);
    }
    @GetMapping("/all/by/role")
    public List<User> getUsersByRole(@RequestParam String userRole){
        return userService.getUsersByRole(userRole);
    }
    // it gives filter data which students is not assigned in Batch {name of this method is not proper}
    @GetMapping("/all/students")
    public List<User> getAllStudents() {
        return userService.getAllStudents();
    }

    @GetMapping("/get/notification/id")
    public ApiResponse<List<Notification>> getAllNotificationByUserId(@RequestParam String userId) {
        return new ApiResponse<>(userService.getAllNotificationByUserId(userId),HttpStatus.ACCEPTED);
    }

    @PutMapping("/mark/notification")
    public ApiResponse<Boolean> markNotificationRead (@RequestParam String userId, String notificationId) {
        return new ApiResponse<>(userService.markNotificationRead(userId, notificationId),HttpStatus.ACCEPTED);
    }




}
