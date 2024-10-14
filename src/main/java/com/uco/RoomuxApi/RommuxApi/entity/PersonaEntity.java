package com.uco.RoomuxApi.RommuxApi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "persona")
public class PersonaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "identificador", columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID identificador;

    @Column(name = "nombre", length = 30)
    private String nombre;

    @Column(name = "numeroIdentificacion", length = 12)
    private String id;


    @Column(name = "correoUsuario",unique = true,length = 30)
    private String correo;

}
