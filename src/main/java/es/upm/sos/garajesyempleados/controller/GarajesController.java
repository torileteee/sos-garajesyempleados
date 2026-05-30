package es.upm.sos.garajesyempleados.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.upm.sos.garajesyempleados.exception.GarajeExistsException;
import es.upm.sos.garajesyempleados.model.Garaje;
import es.upm.sos.garajesyempleados.repository.GarajeRepository;
import es.upm.sos.garajesyempleados.service.GarajeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/garajes")
@XmlRootElement
@AllArgsConstructor
public class GarajesController {
    private final GarajeRepository repo;
    private final GarajeService service;

    @PostMapping()
    ResponseEntity<Void> nuevoGaraje(@Valid @RequestBody Garaje nuevo) {
        if (!service.buscarPorNombreYDireccion(nuevo.getNombre(), nuevo.getDireccion())) {
            Garaje garaje = service.crearGaraje(nuevo);
            return ResponseEntity.created(linkTo(GarajesController.class).slash(garaje.getId()).toUri()).build();
        }
        else throw new GarajeExistsException(nuevo.getNombre(), nuevo.getDireccion());
    }
}
