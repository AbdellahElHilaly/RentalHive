package com.youcode.rentalhive.dao.repository;

import com.youcode.rentalhive.dao.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
<<<<<<< HEAD


=======
    Optional<User> findByEmail(String email);
>>>>>>> 5af98cae50943c7864b9e2cb738988d35259dee3
}
