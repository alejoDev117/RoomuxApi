package com.uco.RoomuxApi.RommuxApi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "reserva")
public class ReservaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "identificador", columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID identificador;

    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private UsuarioEntity autor;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    @Column(name = "fecha_inicio", nullable = false)
    private Date fechaInicio;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    @Column(name = "fecha_fin", nullable = false)
    private Date fechaFin;

    @Column(name = "tipo_reserva", nullable = false)
    private String tipoReserva;

    @Column(name = "frecuencia", nullable = false)
    private String frecuencia;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    @Column(name = "hora_creacion", nullable = false)
    private LocalDateTime horaCreacion;


    @Column(name = "sala", nullable = false)
    private String nombreSala;

    @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<DetalleReservaEntity> detalleReserva;
}


