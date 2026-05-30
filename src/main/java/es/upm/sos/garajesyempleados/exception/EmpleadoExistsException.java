package es.upm.sos.garajesyempleados.exception;

public class EmpleadoExistsException extends RuntimeException {
    public EmpleadoExistsException(String nombre) {
        super("El empleado con nombre " + nombre + " ya existe.");
    }
}
