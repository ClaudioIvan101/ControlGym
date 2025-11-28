package Socios;

import java.util.List;

public class CrudSocios {
    private final List<Socio> socios;
    
    public CrudSocios(List<Socio> socios) {
        this.socios = socios;
    }

    public void agregarSocio(Socio socio) {
        socios.add(socio);
    }

    public Socio buscarSocio(int dni) {
        for (Socio s : socios) {
            if (s.getDni() == dni) {
                return s;
            }
        }
        return null;
    }

    public boolean eliminarSocio(int dni) {
        Socio encontrado = buscarSocio(dni);
        if (encontrado != null) {
            socios.remove(encontrado);
            return true;
        }
        return false;
    }

    public List<Socio> obtenerTodos() {
        return socios;
    }
}
