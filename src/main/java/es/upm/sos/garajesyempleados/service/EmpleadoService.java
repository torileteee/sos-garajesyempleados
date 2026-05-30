package es.upm.sos.garajesyempleados.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import es.upm.sos.garajesyempleados.model.Empleado;
import es.upm.sos.garajesyempleados.repository.EmpleadoRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmpleadoService {
    private final EmpleadoRepository repo;

    public boolean existeEmpleado(String nombre) {
        return repo.existsByNombre(nombre);
    }

    public Empleado crearEmpleado(Empleado empleado) {
        return repo.save(empleado);
    }

    public Optional<Empleado> buscarPorId(Integer id) {
        return repo.findById(id);
    }

    public Page<Empleado> buscarEmpleados(String startsWith, int page, int size) {
        Pageable paginable = PageRequest.of(page, size);
        if (startsWith != null) 
            return repo.findByNombreStartsWith(startsWith, paginable);
        else return repo.findAll(paginable);
    }

    public boolean existeEmpleadoPorId(Integer id) {
        return repo.existsById(id);
    }

    public void eliminarEmpleado(Integer id) {
        repo.deleteById(id);
    }
}
