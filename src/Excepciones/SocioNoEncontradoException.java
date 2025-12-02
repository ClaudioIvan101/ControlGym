package Excepciones;

public class SocioNoEncontradoException extends RuntimeException {
  public SocioNoEncontradoException(int dni) {
    super("No se encontró el socio con DNI " + dni);
  }
}
