package com.uco.RoomuxApi.RommuxApi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "usuario")
public class UsuarioEntity {
    @Id
    @Column(name = "correoElectronico",length = 30,unique = true)
    private String correoElectronico;

    @Column(name = "password",length = 30)
    private String password;
}
