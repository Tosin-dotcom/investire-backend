package com.tosin.investire.user;


import com.tosin.investire.commons.model.Response;
import com.tosin.investire.security.dto.TokenDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.tosin.investire.commons.Constants.USER_API_BASE_URL;

@RestController
@RequestMapping(USER_API_BASE_URL)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("profile")
    public ResponseEntity<Response<TokenDetails>> getUserDetails() {

        TokenDetails userDetails = userService.getUserDetails();
        Response<TokenDetails> response = Response.<TokenDetails>builder()
                .body(userDetails)
                .status(true)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
