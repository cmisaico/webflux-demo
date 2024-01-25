package com.sermaluc.services;

import com.sermaluc.models.UserRequestDTO;
import com.sermaluc.models.UserResponseDTO;
import reactor.core.publisher.Mono;

public interface UserService {
    public Mono<UserResponseDTO> save(UserRequestDTO userRegisterReq);
}
