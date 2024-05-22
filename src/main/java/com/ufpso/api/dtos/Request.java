package com.ufpso.api.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class Request<T> {

    @JsonProperty
    @NotNull(message = "message id should not be null")
    private String messageID;

    @JsonProperty
    @NotNull(message = "data should not be null")
    @Valid
    private T data;

    public Request(String messageID, T data) {
        this.messageID = messageID;
        this.data = data;
    }
}
