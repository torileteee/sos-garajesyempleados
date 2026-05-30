package es.upm.sos.garajesyempleados.model;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "garajes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Garaje extends RepresentationModel<Garaje> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "El nombre es obligatorio y no puede ser null")
    private String nombre;
    private String direccion;
    @NotNull(message = "El telefono es obligatorio y no puede ser null")
    private int telefono;
}
