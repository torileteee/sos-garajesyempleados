package es.upm.sos.garajesyempleados.service;

import java.util.List;

import org.springframework.stereotype.Service;

import es.upm.sos.garajesyempleados.model.Empleado;
import es.upm.sos.garajesyempleados.model.Garaje;
import es.upm.sos.garajesyempleados.model.GarajeEmpleado;
import es.upm.sos.garajesyempleados.model.GarajeEmpleadoId;
import es.upm.sos.garajesyempleados.repository.GarajeEmpleadoRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GarajeEmpleadoService {
    private final GarajeEmpleadoRepository repo;

    public List<GarajeEmpleado> buscarPorGarajeId(int id) {
        return repo.findByGaraje_Id(id);
    }

    public void contratarEmpleadoEnGaraje (GarajeEmpleadoId geId, Garaje garaje, Empleado empleado) {
        // Crear PK compuesta
        geId.setGarajeId(garaje.getId());

        // crear la relacion
        GarajeEmpleado relacion = new GarajeEmpleado();

        relacion.setId(geId);
        relacion.setGaraje(garaje);
        relacion.setEmpleado(empleado);

        // Guardar la relacion
        repo.save(relacion);
    }
}
