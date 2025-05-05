package com.tosin.investire.user;


import com.tosin.investire.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserDto createNewUser(UserDto userDto) {

        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.createNewUser(userDto);

    }

    public Optional<UserDto> getOptionalUserByEmail(String email) {

        return userRepository.findUserByEmail(email);
    }




}
