package es.upm.sos.garajesyempleados.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.upm.sos.garajesyempleados.assembler.EmpleadoModelAssembler;
import es.upm.sos.garajesyempleados.exception.EmpleadoExistsException;
import es.upm.sos.garajesyempleados.exception.EmpleadoNotFoundException;
import es.upm.sos.garajesyempleados.model.Empleado;
import es.upm.sos.garajesyempleados.repository.EmpleadoRepository;
import es.upm.sos.garajesyempleados.service.EmpleadoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*   ;

import java.util.List;

//import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@RestController
@RequestMapping("/empleados")
@XmlRootElement
@AllArgsConstructor
public class EmpleadosController {

    private final EmpleadoService service;
    // Acceso al repositorio empleado para poder realizar consultas SQL
    private final EmpleadoRepository repository;
    // Necesarios para navegabiliad
    private PagedResourcesAssembler<Empleado> pagedResourcesAssembler;
    private EmpleadoModelAssembler empleadoModelAssembler;

    @PostMapping()
    ResponseEntity<Void> nuevoEmpleado(@Valid @RequestBody Empleado nuevoE) {
        if (!service.existeEmpleado(nuevoE.getNombre())) {
            Empleado empleado  = service.crearEmpleado(nuevoE);
            return ResponseEntity.created(linkTo(EmpleadosController.class).slash(empleado.getId()).toUri()).build();
        }
        throw new EmpleadoExistsException(nuevoE.getNombre());
    }

    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml"})
    public
    Empleado getEmpleado(@PathVariable Integer id) {
        // Encuentra al empleado OR ELSE lanza excepcion
        Empleado empleado = service.buscarPorId(id).orElseThrow(() -> new EmpleadoNotFoundException(id));
        empleado.add(linkTo(methodOn(EmpleadosController.class).getEmpleado(id)).withSelfRel());
        return empleado;
    }

    @GetMapping(value = "", produces = {"application/json", "application/xml"})
    ResponseEntity<PagedModel<Empleado>> getEmpleados(@RequestParam(defaultValue = "", required = false) String startsWith, 
            @RequestParam(defaultValue = "0", required = false) int page, 
            @RequestParam(defaultValue = "2", required = false) int size) {
        Page<Empleado> empleados = service.buscarEmpleados(startsWith, page, size);
        return ResponseEntity.ok(pagedResourcesAssembler.toModel(empleados, empleadoModelAssembler));
    }

    @PutMapping("/{id}")
    ResponseEntity<Void> replaceEmpleado(@PathVariable Integer id, @Valid @RequestBody Empleado empleado) {
        // Encuentra al empleado (Optional) OR ELSE lanza excepcion
        // Si Optional esta empty no se ejecuta lo de map
        service.buscarPorId(id).map(Empleado -> {
            Empleado.setNombre(empleado.getNombre());
            return service.crearEmpleado(Empleado); // aqui crearEmpleado lo que esta es guardando en la BD ese empleado
            // o sea, actualizandolo, no creandolo realmente (lo hace JPA por tener el mismo ID)
        }).orElseThrow(() -> new EmpleadoNotFoundException(id));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteEmpleado(@PathVariable Integer id) {
        if (service.existeEmpleadoPorId(id)) service.eliminarEmpleado(id);
        else throw new EmpleadoNotFoundException(id);
        return ResponseEntity.noContent().build();
    }
}
