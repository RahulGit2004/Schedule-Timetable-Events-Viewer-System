package in.codingAge.scheduleSystems.repository;

import in.codingAge.scheduleSystems.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String > {
    User findByPhoneNumber(String phoneNumber);

    User findByUserId(String creatorId);

    List<User> findAllByUserRole(String userRole);
}
