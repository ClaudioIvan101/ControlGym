package Excepciones;

public class MembresiaNoEncontradaException extends RuntimeException {
    public MembresiaNoEncontradaException(int id) {
        super("No se encontró la membresía con ID " + id);
    }
}
