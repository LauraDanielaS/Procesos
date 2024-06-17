package com.ufpso.api.controllers;

import com.ufpso.api.dtos.AuthRequest;
import com.ufpso.api.dtos.GenericRequest;
import com.ufpso.api.dtos.GenericResponse;
import com.ufpso.api.dtos.Response;
import com.ufpso.api.enums.Messages;
import com.ufpso.api.models.User;
import com.ufpso.api.services.AuthService;
import com.ufpso.api.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class UserController {

    public final AuthService authService;
    public final UserService userService;

    public UserController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<GenericResponse<?>> login(@RequestBody @Valid GenericRequest<AuthRequest> request) {
        GenericResponse<?> response = new GenericResponse<>(new Response<>(authService.login(request.requestMessage().getData()), HttpStatus.OK.value()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<GenericResponse<?>> createUser(@RequestBody @Valid GenericRequest<User> request){
        userService.createUser(request.requestMessage().getData());
        GenericResponse<?> response = new GenericResponse<>(new Response<>(Messages.USER_CREATED.getMessage(), HttpStatus.OK.value()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
