package com.enter_auth_service.service;

import com.enter_auth_service.modal.User;
import com.enter_auth_service.repository.UserRepo;
import com.enter_auth_service.security.SecurityConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;

    // POST METHOD
    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }
    // LOGIN METHOD
    public String login(String email, String password){
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "USER NOT FOUND"));
        if (!passwordEncoder.matches(password, user.getPassword())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }
        return "Login Successfull";
    }
//    public User login(String email){
//        return userRepo.findByEmail(email)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//    }
    // FIND METHOD
    public User findUser(Long id){
        return userRepo.findById(id).orElseThrow(() -> new RuntimeException("User Not Found with id : "+ id));
    }
    // DELETE USER BY ID METHOD
    public void deleteById(Long id){
        if (userRepo.existsById(id)){
            userRepo.deleteById(id);
            System.out.println("User  with ID "+ id +"is Deleted successfully!" );
        }else {
            throw  new RuntimeException("Cannot delete: User not found with id: " + id);
        }
    }
}

