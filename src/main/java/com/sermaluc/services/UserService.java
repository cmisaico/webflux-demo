package com.sermaluc.services;

import com.sermaluc.models.dtos.UserRequestDTO;
import com.sermaluc.models.dtos.UserResponseDTO;
import reactor.core.publisher.Mono;

public interface UserService {
    public Mono<UserResponseDTO> save(UserRequestDTO userRegisterReq);
}
