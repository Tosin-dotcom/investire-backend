package com.tosin.investire.commons.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request<T> {

    private T body;
}
