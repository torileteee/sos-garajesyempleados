package es.upm.sos.garajesyempleados.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "garaje_empleado")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GarajeEmpleado {
    @EmbeddedId //Indica GarajeEmpleadoId es clave primaria compuesta
    private GarajeEmpleadoId id;

    @ManyToOne // Cada instancia GarajeEmpleado va a tener un empleado
    @MapsId("empleadoId") //Establece asociación id.empleadoId -> Empleado.id
                        // = a id.setEmpleadoId(empleado.getId())
    @JoinColumn(name = "empleado_id") //Establece asociación BD entre:
                                        // FK (empleado_id)
                                        // PK de la entidad (Empleado.id)
    private Empleado empleado;

    @ManyToOne // Cada instancia GarajeEmpleado va a tener un garaje
    @MapsId("garajeId") // Asocia la clave primaria al campo correspondiente
    @JoinColumn(name = "garaje_id")
    private Garaje garaje;
    
}
