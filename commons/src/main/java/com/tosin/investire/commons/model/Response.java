package com.tosin.investire.commons.model;


import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {

    private boolean status;
    private T body;

}
