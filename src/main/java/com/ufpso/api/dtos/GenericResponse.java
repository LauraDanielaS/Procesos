package com.ufpso.api.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GenericResponse<T> {

    private Response<T> responseMessage;

    public GenericResponse(Response<T> responseMessage) {
        this.responseMessage = responseMessage;
    }
}
