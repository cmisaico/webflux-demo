package com.sermaluc.services.impl;

import com.sermaluc.helpers.JwtUtil;
import com.sermaluc.models.dtos.PhoneDto;
import com.sermaluc.models.entities.Phone;
import com.sermaluc.models.entities.TokenInfo;
import com.sermaluc.models.entities.User;
import com.sermaluc.exceptions.EmailExistException;
import com.sermaluc.helpers.DateUtil;
import com.sermaluc.models.dtos.TokenInfoDTO;
import com.sermaluc.models.dtos.UserRequestDTO;
import com.sermaluc.models.dtos.UserResponseDTO;
import com.sermaluc.repositories.UserRepository;
import com.sermaluc.services.UserService;
import com.sermaluc.services.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import java.time.LocalDateTime;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public Mono<UserResponseDTO> save(UserRequestDTO userDto) {
        if(userRepository.existsByEmail(userDto.getEmail())){
            return Mono.error(new EmailExistException("El correo ya registrado"));
        }
        TokenInfoDTO jwt = JwtUtil.create(userDto.getEmail());

        return Mono.just(userDto)
                .map(UserMapper.INSTANCE::map)
                .map(user -> {
                    user.setTokenInfo(TokenInfo.builder()
                            .token(jwt.getToken())
                            .expirationDate(jwt.getExpirationDate()).build()
                    );
                    return user;
                })
                .map(userRepository::save)
                .map(UserMapper.INSTANCE::map);

    }

}
