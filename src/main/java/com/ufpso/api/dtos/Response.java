package com.ufpso.api.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Response<T> {

    @JsonProperty
    private T message;

    @JsonProperty
    private LocalDate date;

    @JsonProperty
    private Integer statusCode;

    public Response(T message, LocalDate date, Integer statusCode) {
        this.message= message;
        this.date= date;
        this.statusCode = statusCode;
    }

}
