package Reportes;

import Asistencias.Asistencia;
import Cuotas.Cuota;
import Socios.Socio;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
public class Reporte {
    private List<Socio> socios;
    private List<Asistencia> asistencias;

    public Reporte(List<Socio> socios, List<Asistencia> asistencias) {
        this.socios = socios;
        this.asistencias = asistencias;

    }
    public double montoTotalFacturado() {
        return socios.stream()
                .flatMap(s -> s.getCuotas().stream())
                .filter(Cuota::isPagado)
                .mapToDouble(Cuota::getMonto)
                .sum();
    }


    public List<Asistencia> asistenciasHoy() {
        LocalDate hoy = LocalDate.now();
        return asistencias.stream()
                .filter(a -> a.getFechaHora().toLocalDate().equals(hoy))
                .toList();
    }

    public Map<Socio, Long> contarAsistenciasPorSocio() {
        return asistencias.stream()
                .collect(Collectors.groupingBy(Asistencia::getSocio, Collectors.counting()));
    }


    public List<Socio> sociosConCuotaVencida() {
        LocalDate hoy = LocalDate.now();

        return socios.stream()
                .filter(s -> {
                    Cuota ultima = s.getUltimaCuotaPagada();
                    return ultima == null || ultima.getFechaVencimiento().isBefore(hoy);
                })
                .toList();
    }


    public List<Socio> sociosConCuotaAlDia() {
        LocalDate hoy = LocalDate.now();

        return socios.stream()
                .filter(s -> {
                    Cuota ultima = s.getUltimaCuotaPagada();
                    return ultima != null && !ultima.getFechaVencimiento().isBefore(hoy);
                })
                .toList();
    }
    public LocalDate vencimientoDeSocio(int dni) {
        for (Socio s : socios) {
            if (s.getDni() == dni) {
                Cuota ultima = s.getUltimaCuotaPagada();
                if (ultima != null) {
                    return ultima.getFechaVencimiento();
                } else {
                    return null;
                }
            }
        }
        return null;
    }
}
