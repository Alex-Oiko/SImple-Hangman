package com.nexmo.task.service;

import com.nexmo.task.domain.AppUser;
import com.nexmo.task.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public AppUser createUser(String username){
        AppUser appUser = new AppUser();
        appUser.setUsername(username);
        return userRepository.save(appUser);
    }

    public List<AppUser> findAllUsers(){
        final List<AppUser> resultList = new ArrayList<>();
        final Iterable<AppUser> all = userRepository.findAll();
        all.forEach(resultList::add);
        return  resultList;
    }
}
