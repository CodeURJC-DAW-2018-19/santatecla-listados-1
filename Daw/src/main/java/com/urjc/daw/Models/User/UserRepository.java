package com.urjc.daw.Models.User;

import com.urjc.daw.Models.User.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends JpaRepository<User, Integer> {
    User findById(Long id);
    User findAllByName(String name);

    User findUserByEmail(String email);

    List<User> findAllByRolesEquals(String roles);
    Page<User> findAllByIdNotNull(Pageable pageRequest);
}