package Asistencias;

import Membresias.Membresia;
import Socios.Socio;
import java.time.LocalDateTime;

public class Asistencia {
    private int id;
    private Socio socio;
    private Membresia membresia;
    private LocalDateTime fechaHora;

    public Asistencia(int id,Socio socio, LocalDateTime fechaHora, Membresia membresia) {
        this.socio = socio;
        this.membresia = membresia;
        this.fechaHora = fechaHora;
        this.id = id;
    }

    public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    public Membresia getMembresia() {
        return membresia;
    }

    public void setMembresia(Membresia membresia) {
        this.membresia = membresia;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //@Override
    //public String toString() {
    //return "Asistencia #" + id +
    //          " | Socio: " + socio.getNombre() +
    //          " | DNI: " + socio.getDni() +
    //        " | Membresía: " + membresia.getNombre() +
    //          " | Fecha/Hora: " + fechaHora;
    }

