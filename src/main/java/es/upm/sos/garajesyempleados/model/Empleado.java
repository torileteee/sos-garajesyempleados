package es.upm.sos.garajesyempleados.model;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*; //Librería java para reducir la cantidad de código

@Entity //Crea automáticamente la tabla en la base de datos
@Table(name = "empleados") // Necesario para indicar el nombre de la tabla en la base de datos
@Data // Lombok genera automáticamente los getters, setters, equals, hashcode y toString
@NoArgsConstructor // Crea un constructor vacío
@AllArgsConstructor // Crea un constructor con todos los campos
public class Empleado extends RepresentationModel<Empleado> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @EqualsAndHashCode.Include // campo considerado en el método equals y hash code
    @NotNull(message = "El nombre es obligatorio y no puede ser null")
    private String nombre;
}
