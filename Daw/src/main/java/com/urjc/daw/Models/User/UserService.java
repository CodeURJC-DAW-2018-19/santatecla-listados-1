package com.urjc.daw.Models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository  userRepository){
        this.userRepository = userRepository;
    }

    public User findOneById(Long id){return userRepository.findById(id);}

    public List<User> findAll(){return userRepository.findAll();}

    public long countUser(){ return userRepository.count();}

    public void seveUser(User user){ userRepository.save(user);}

    public User findUserByEmail(String email){return userRepository.findUserByEmail(email);}

    public Page<User> findAll(PageRequest pageRequest){return userRepository.findAll(pageRequest);}

}
