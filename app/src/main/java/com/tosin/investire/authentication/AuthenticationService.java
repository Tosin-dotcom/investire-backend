package com.tosin.investire.authentication;


import com.tosin.investire.authentication.dto.LoginDto;
import com.tosin.investire.commons.exception.InvestireException;
import com.tosin.investire.security.dto.TokenDetails;
import com.tosin.investire.security.service.SecurityService;
import com.tosin.investire.user.UserService;
import com.tosin.investire.user.dto.UserDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.tosin.investire.commons.Constants.AUTH_TOKEN_COOKIE;

@Service
@RequiredArgsConstructor
public class AuthenticationService {


    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final SecurityService securityService;


    private static final String LOGIN_ERROR_MESSAGE = "Invalid login details";


    public UserDto registerUser(UserDto userDto) {

        return userService.createNewUser(userDto);
    }

    public TokenDetails loginUser(LoginDto loginDto, HttpServletResponse response) {

        UserDto userDto = userService.getOptionalUserByEmail(loginDto.getEmail())
                .orElseThrow(() -> new InvestireException(HttpStatus.FORBIDDEN, LOGIN_ERROR_MESSAGE));
        boolean matches = passwordEncoder.matches(loginDto.getPassword(), userDto.getPassword());
        if (!matches) {
            throw new InvestireException(HttpStatus.FORBIDDEN, LOGIN_ERROR_MESSAGE);
        }
        TokenDetails tokenDetails= TokenDetails.builder()
                .email(userDto.getEmail())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .build();
        Cookie cookie = createAuthTokenCookie(tokenDetails);
        response.addCookie(cookie);
        return tokenDetails;
    }

    private Cookie createAuthTokenCookie(TokenDetails tokenDetails) {

        String authenticationToken = securityService.generateToken(tokenDetails);
        Cookie cookie = new Cookie(AUTH_TOKEN_COOKIE, authenticationToken);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(2 * 24 * 60 * 60);

        return cookie;
    }


}
