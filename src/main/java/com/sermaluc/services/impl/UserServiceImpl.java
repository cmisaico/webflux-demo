package com.sermaluc.services.impl;

import com.sermaluc.helpers.JwtUtil;
import com.sermaluc.entities.Phone;
import com.sermaluc.entities.TokenInfo;
import com.sermaluc.entities.User;
import com.sermaluc.exceptions.EmailExistException;
import com.sermaluc.helpers.DateUtil;
import com.sermaluc.models.TokenInfoDTO;
import com.sermaluc.models.UserRequestDTO;
import com.sermaluc.models.UserResponseDTO;
import com.sermaluc.repositories.UserRepository;
import com.sermaluc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    JwtUtil jwtUtil;

    @Autowired
    UserServiceImpl(UserRepository userRepository, JwtUtil jwtUtil){
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @Transactional
    @Override
    public Mono<UserResponseDTO> save(UserRequestDTO userDto) {
        if(userRepository.existsByEmail(userDto.getEmail())){
            return Mono.error(new EmailExistException("El correo ya registrado"));
        }
        TokenInfoDTO jwt = jwtUtil.create(userDto.getEmail());
        User user = User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .created(LocalDateTime.now())
                .tokenInfo(TokenInfo.builder()
                    .token(jwt.getToken())
                    .expirationDate(jwt.getExpirationDate()).build()
                )
                .lastLogin(LocalDateTime.now())
                .phones(userDto.getPhones().stream().map(phoneDto ->
                                Phone.builder()
                                        .number(phoneDto.getNumber())
                                        .citycode(phoneDto.getCitycode())
                                        .contrycode(phoneDto.getContrycode()).build())
                .toList()).build();

        User newuser = userRepository.save(user);

        UserResponseDTO userResponseDTO = UserResponseDTO.builder()
                .id(newuser.getId().toString())
                .name(newuser.getName())
                .email(newuser.getEmail())
                .phones(newuser.getPhones().stream().map(phone ->
                        com.sermaluc.models.PhoneDto.builder()
                                .number(phone.getNumber())
                                .citycode(phone.getCitycode())
                                .contrycode(phone.getContrycode()).build())
                        .toList())
                .created(DateUtil.formatearFecha(newuser.getCreated()))
                .modified(DateUtil.formatearFecha(newuser.getModified()))
                .lastLogin(DateUtil.formatearFecha(newuser.getLastLogin()))
                .isActive(newuser.isActive())
                .token(newuser.getTokenInfo().getToken())
                .build();

        return Mono.just(userResponseDTO);
    }

}
