
package com.app.productos.modelo;

public class Proveedor {
    private int id;
    private String dni;
    private String nombres;
    private String correo;
    private double pago;


    public Proveedor() {
    }

    public Proveedor(int id, String dni, String nombres, String correo, double pago) {
        this.id = id;
        this.dni = dni;
        this.nombres = nombres;
        this.correo = correo;
        this.pago = pago;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }    
    
     public double getPago() {
        return pago;
    }

    public void setPago(double pago) {
        this.pago = pago;
    }
    
}
