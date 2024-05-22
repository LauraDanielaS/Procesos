package com.ufpso.api.dtos;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record GenericRequest<T>(@NotNull @Valid Request<T> requestMessage) {
}
