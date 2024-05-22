package com.ufpso.api.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class Request<T> {

    @JsonProperty
    @NotNull
    private String messageID;

    @JsonProperty
    @NotNull
    @Valid
    private T data;

    public Request(String messageID, T data) {
        this.messageID = messageID;
        this.data = data;
    }
}
