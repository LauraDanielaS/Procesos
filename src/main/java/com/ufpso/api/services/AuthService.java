package com.ufpso.api.services;

import com.ufpso.api.dtos.AuthRequest;
import com.ufpso.api.dtos.AuthResponse;

public interface AuthService {
    AuthResponse login(AuthRequest authRequest);

}
