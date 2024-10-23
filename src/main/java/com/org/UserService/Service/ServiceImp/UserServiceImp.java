package com.org.UserService.Service.ServiceImp;

import com.org.UserService.DTO.LoginRequest;
import com.org.UserService.DTO.ResponseDTO;
import com.org.UserService.DTO.UserDTO;
import com.org.UserService.Entity.User;
import com.org.UserService.Repository.UserRepository;
import com.org.UserService.Security.JWT.JwtUtils;
import com.org.UserService.Security.Service.UserDetailsServiceImp;
import com.org.UserService.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImp userDetailsServiceImp;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseDTO registerUser(@Valid UserDTO userDTO) {
       try{
           if(userRepository.findByEmail(userDTO.getEmail()) != null) {
               return ResponseDTO.builder().message("Email Already Exists").build();
           }
           User user= User.builder()
                   .email(userDTO.getEmail())
                   .name(userDTO.getUserName())
                   .role(userDTO.getRole())
                   .password(passwordEncoder.encode(userDTO.getPassword()))
                   .build();
           userRepository.save(user);
       }catch (Exception e){
           return ResponseDTO.builder().message(e.getMessage()).build();
       }
        return ResponseDTO.builder().message("User Registered").data(userDTO).build();
    }
    @Override
    public ResponseDTO loginUser(@Valid LoginRequest request) {
        ResponseDTO res=new ResponseDTO();
       try{
           if(userRepository.findByEmail(request.getEmail()) == null) {
               return ResponseDTO.builder().message("User is  Not Found").build();
           }
           authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
           UserDetails userDetails = userDetailsServiceImp.loadUserByUsername(request.getEmail());
           String token= jwtUtils.generateToken(userDetails);
           res.setData(token);
           res.setMessage("User logged in");
       }
       catch (Exception e){
           res.setMessage(e.getMessage());
           e.printStackTrace();
       }
       return res;
    }
}
