package com.sermaluc.models;

import com.sermaluc.entities.Phone;
import com.sermaluc.valids.ClaveValida;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Builder
public class UserRequestDTO {

    @NotBlank(message = "{user.nombre.vacio}")
    @Size(min = 3, max = 50, message = "{user.nombre.longitud}")
    private String name;

    @NotBlank(message = "{user.email.vacio}")
    @Email(message = "{user.email.valido}")
    private String email;

    @NotBlank(message = "{user.password.vacio}")
    @Size(min = 6, message = "{user.password.longitud}")
    @ClaveValida(message = "{user.password.valido}")
    private String password;

    @NotNull(message = "{user.telefonos.vacio}")
    private List<PhoneDto> phones;

}
