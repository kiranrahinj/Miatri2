package com.org.UserService.Service;

import com.org.UserService.DTO.LoginRequest;
import com.org.UserService.DTO.ResponseDTO;
import com.org.UserService.DTO.UserDTO;

public interface UserService {
    ResponseDTO registerUser(UserDTO userDTO);

    ResponseDTO loginUser(LoginRequest request);
}
