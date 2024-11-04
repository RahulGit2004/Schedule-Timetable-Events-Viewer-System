package in.codingAge.scheduleSystems.service;

import in.codingAge.scheduleSystems.model.User;
import in.codingAge.scheduleSystems.model.request.SignUpRequest;
import in.codingAge.scheduleSystems.model.response.LoginResponse;

import java.util.List;

public interface UserService {


    Boolean signUp(SignUpRequest signUpRequest);

    User getUserByPhoneNumber(String phoneNumber);

    LoginResponse singIn(String phoneNumber, String password);

    User getUserByUserId(String creatorId);

    User saveUpdates(User user);

    List<User> getUsersByRole(String userRole);
}
