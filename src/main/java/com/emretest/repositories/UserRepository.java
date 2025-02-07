package com.emretest.repositories;

import com.emretest.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


    @Transactional(readOnly = true) // Okuma işlemi
    @Query("SELECT u FROM User u")
    Page<User> searchUser(Pageable pageable);

    @Transactional(readOnly = true) // Okuma işlemi
    Page<User> findAll(Pageable pageable);

    @Transactional(readOnly = true) // Okuma işlemi
    Optional<User> findById(Integer id);

}
