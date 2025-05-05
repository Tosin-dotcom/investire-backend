package com.tosin.investire.commons;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {

    public static final String USER = "user";
    public static final String AUTH_TOKEN_COOKIE = "AUTH_TOKEN";
    public static final String API_BASE_URL = "/api/v1/";
    public static final String AUTH_API_BASE_URL = API_BASE_URL + "auth";


}
