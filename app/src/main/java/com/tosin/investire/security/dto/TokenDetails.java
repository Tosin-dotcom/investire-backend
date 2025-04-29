package com.tosin.investire.security.dto;


import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenDetails {

    private UUID id;
    private String name;
    private String email;

}
