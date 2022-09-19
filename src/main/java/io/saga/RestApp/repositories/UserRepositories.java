package io.saga.RestApp.repositories;

import io.saga.RestApp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Sagadat Kuandykov
 */
@Repository
public interface UserRepositories extends JpaRepository<User, Integer> {

}
