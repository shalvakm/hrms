package com.newhrms.userservice.Controller;


import com.newhrms.userservice.Exception.ResourceNotFoundException;
import com.newhrms.userservice.Models.UserLogin;
import com.newhrms.userservice.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/userlogin")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserLoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public List<UserLogin> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserLogin> getUserById(@PathVariable(value = "userId") long userId)
            throws ResourceNotFoundException {
        UserLogin userLogin = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
        return ResponseEntity.ok().body(userLogin);
    }

    @PostMapping("")
//    @RequestMapping(value = "/users", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody UserLogin createUser(@Valid @RequestBody UserLogin userLogin) {

        return userRepository.save(userLogin);
    }


    @PutMapping("/{userId}")
    public @ResponseBody void updateEmployee(@PathVariable(value = "userID") long userId,
                                             @Valid @RequestBody UserLogin userDetails) throws ResourceNotFoundException
    {
        UserLogin userLogin = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

        userLogin.setEmailId(userLogin.getEmailId());
        userLogin.setPassword(userLogin.getPassword());
        userDetails.setUsername(userDetails.getUsername());

        final UserLogin updatedUser = userRepository.save(userLogin);

//        return userRepository.save(userDetails);
    }
    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable(value = "userId") long userId)
            throws ResourceNotFoundException {
        UserLogin userLogin = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

        userRepository.delete(userLogin);
        return "deleted";
    }
}