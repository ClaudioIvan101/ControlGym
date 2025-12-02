package Cuotas;

import Membresias.Membresia;
import Socios.Socio;

import java.time.LocalDate;

public class Cuota {
    private int id;
    private Socio socio;
    private Membresia membresia;
    private LocalDate fechaPago;
    private LocalDate fechaVencimiento;
    private double monto;
    private boolean pagado;

    public Cuota(Socio socio, Membresia membresia, LocalDate fechaPago) {
        this.socio = socio;
        this.membresia = membresia;
        this.fechaPago = fechaPago;
        this.monto = membresia.getPrecio();
        this.pagado = true;
        this.fechaVencimiento = fechaPago.plusMonths(1);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    @Override
    public String toString() {
        return "Cuota{" +
                "id=" + id +
                ", socio=" + socio +
                ", membresia=" + membresia +
                ", fechaPago=" + fechaPago +
                ", fechaVencimiento=" + fechaVencimiento +
                ", monto=" + monto +
                ", pagado=" + pagado +
                '}';
    }
}
