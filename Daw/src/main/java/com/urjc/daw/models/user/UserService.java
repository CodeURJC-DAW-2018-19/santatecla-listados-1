package com.urjc.daw.models.user;

import com.urjc.daw.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    UserRepository userRepository;

    public User findUserByName(String name) {
        return userRepository.findByName(name);
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public List<User> findAllUser() {
        return userRepository.findAll();
    }
    public Optional<User> findById(long id){
        return userRepository.findByIdUser(id);
    }
}
