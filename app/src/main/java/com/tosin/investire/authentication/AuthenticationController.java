package com.tosin.investire.authentication;


import com.tosin.investire.authentication.dto.LoginDto;
import com.tosin.investire.commons.model.Request;
import com.tosin.investire.commons.model.Response;
import com.tosin.investire.security.dto.TokenDetails;
import com.tosin.investire.user.dto.UserDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.tosin.investire.commons.Constants.AUTH_API_BASE_URL;

@RestController
@RequestMapping(AUTH_API_BASE_URL)
@RequiredArgsConstructor
public class AuthenticationController {


    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<Response<UserDto>> register(@RequestBody Request<UserDto> request) {

        UserDto registerUser = authenticationService.registerUser(request.getBody());
        Response<UserDto> response = Response.<UserDto>builder()
                .body(registerUser)
                .status(true)
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PostMapping("login")
    public ResponseEntity<Response<TokenDetails>> loginUser(@RequestBody Request<LoginDto> request,
            HttpServletResponse response) {

        TokenDetails tokenDetails = authenticationService.loginUser(request.getBody());
        Cookie authTokenCookie = authenticationService.createAuthTokenCookie(tokenDetails);
        Response<TokenDetails> apiResponse = Response.<TokenDetails>builder()
                .body(tokenDetails)
                .status(true)
                .build();
        response.addCookie(authTokenCookie);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
