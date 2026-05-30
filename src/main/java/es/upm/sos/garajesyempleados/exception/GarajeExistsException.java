package es.upm.sos.garajesyempleados.exception;

public class GarajeExistsException extends RuntimeException {
    public GarajeExistsException(String nombre, String direccion) {
        super("Ya existe un garaje con nombre " + nombre + " y direccion " + direccion + ".");
    }
}
