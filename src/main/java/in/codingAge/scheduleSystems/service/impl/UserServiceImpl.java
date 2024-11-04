package in.codingAge.scheduleSystems.service.impl;

import in.codingAge.scheduleSystems.exception.AppException;
import in.codingAge.scheduleSystems.model.User;
import in.codingAge.scheduleSystems.model.request.SignUpRequest;
import in.codingAge.scheduleSystems.model.response.LoginResponse;
import in.codingAge.scheduleSystems.repository.UserRepository;
import in.codingAge.scheduleSystems.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


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


}
