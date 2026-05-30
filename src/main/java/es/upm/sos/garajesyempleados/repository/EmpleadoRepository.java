package es.upm.sos.garajesyempleados.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import es.upm.sos.garajesyempleados.model.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {
    //Buscar si existe por nombre de usuario
    //El nombre del campo tiene que ser el mismo que el campo de la tabla
    boolean existsByNombre(String nombre);
    Page<Empleado> findByNombreStartsWith(@Param("startsWith") String nombreEmpiezaCon, Pageable pageable);
}