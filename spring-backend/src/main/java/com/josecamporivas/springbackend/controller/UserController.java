package com.josecamporivas.springbackend.controller;

import com.josecamporivas.springbackend.exception.UserNotFoundException;
import com.josecamporivas.springbackend.model.User;
import com.josecamporivas.springbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    public User newUser(@RequestBody User newUser){
        return userRepository.save(newUser);
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable Long id){
        return userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException(id));
    }

    @PutMapping("/user/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User newUser){
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    return userRepository.save(user);
                }).orElseThrow(()-> new UserNotFoundException(id));
    }

    @DeleteMapping("/user/{id}")
    public Map<String, String> deleteUser(@PathVariable Long id){
        if(!userRepository.existsById(id))
            throw new UserNotFoundException(id);

        userRepository.deleteById(id);

        Map<String,String> response = new HashMap<String, String>();
        response.put("message", "User with id " + id + " has been deleted successfully");
        return response;
        //return "User with id " + id + " has been deleted successfully";
    }

}
