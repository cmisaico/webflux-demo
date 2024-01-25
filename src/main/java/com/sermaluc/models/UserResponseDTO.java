package com.sermaluc.models;

import lombok.Builder;
import lombok.Data;
import java.util.List;
@Data
@Builder
public class UserResponseDTO {

    private String id;

    private String name;

    private String email;

    private List<PhoneDto> phones;

    private String created;

    private String modified;

    private String lastLogin;

    private boolean isActive;

    private String token;

}
