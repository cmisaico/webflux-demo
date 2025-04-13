package com.sermaluc.controllers;

import com.sermaluc.models.dtos.UserRequestDTO;
import com.sermaluc.models.dtos.UserResponseDTO;
import com.sermaluc.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/user")
@Validated
public class UsuarioController {
    private UserService userService;

    @Autowired
    UsuarioController(UserService userService){
        this.userService = userService;
    }

    @Operation(
            description = "Crea un usuario",
            summary = "Crea un usuario",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Usuario creado con exito"),
                    @ApiResponse(responseCode = "400", description = "Error al crear el usuario"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            }
    )
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<UserResponseDTO> create(@Valid @RequestBody UserRequestDTO userRegisterReqMono) {
        return userService.save(userRegisterReqMono);
    }

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<UserResponseDTO> findAll() {
        return userService.findAll();
    }
}
