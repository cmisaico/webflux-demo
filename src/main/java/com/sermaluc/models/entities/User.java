package com.sermaluc.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @GenericGenerator(name = "UUID")
    @Column(name = "id")
    private UUID id;

    @NotBlank(message = "{user.nombre.vacio}")
    @Size(min = 3, max = 50, message = "{user.nombre.longitud}")
    private String name;

    @NotBlank(message = "{user.email.vacio}")
    @Email(message = "{user.email.valido}")
    private String email;

    @NotBlank(message = "{user.password.vacio}")
    @Size(min = 6, message = "{user.password.longitud}")
    private String password;

    @NotNull(message = "{user.telefonos.vacio}")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Phone> phones;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime modified;

    @CreationTimestamp
    @Column(nullable = true, updatable = true)
    private LocalDateTime lastLogin;

    private boolean isActive;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private TokenInfo tokenInfo;
}
