package es.upm.sos.garajesyempleados.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import es.upm.sos.garajesyempleados.model.Garaje;
import es.upm.sos.garajesyempleados.repository.GarajeRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GarajeService {
    private final GarajeRepository repo;

    public boolean buscarPorNombreYDireccion(String nombre, String direccion) {
        Optional<Garaje> garaje = repo.findByNameAndDirection(nombre, direccion);
        return garaje.isPresent();
    }

    public Garaje crearGaraje(Garaje garaje) {
        return repo.save(garaje);
    }
    
}
