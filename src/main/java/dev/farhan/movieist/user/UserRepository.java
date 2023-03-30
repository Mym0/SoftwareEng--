package dev.farhan.movieist.user;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

//For communication with the DB, fetches users by their email address.
@Repository
public interface UserRepository extends MongoRepository<User, Integer> {

    Optional<User> findByEmail(String email);
    // Optional<User> DeleteUserById(Integer id);
}