package io.saga.RestApp.services;

import io.saga.RestApp.models.User;
import io.saga.RestApp.repositories.UserRepositories;
import io.saga.RestApp.util.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author Sagadat Kuandykov
 */
@Service
@Transactional(readOnly = true)
public class UsersService {
    private final UserRepositories userRepositories;

    @Autowired
    public UsersService(UserRepositories userRepositories) {
        this.userRepositories = userRepositories;
    }

    public List<User> findAll(){
        return userRepositories.findAll();
    }
    public User findOne(int id){
        Optional<User>foundUser = userRepositories.findById(id);

        return foundUser.orElseThrow(UserNotFoundException::new);
    }
    @Transactional
    public void save(User user){
        enrichUser(user);

        userRepositories.save(user);

    }
    private void enrichUser(User user) {
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setCreatedBy("ADMIN");

    }
}
