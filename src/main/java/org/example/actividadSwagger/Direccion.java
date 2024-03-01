package org.example.actividadSwagger;

import javax.persistence.Embeddable;

@Embeddable
public class Direccion {
    private String calle;
    private int CP;
    private String localidad;
    private String provincia;
    private int numero;

    public Direccion() {

    }

    public Direccion(String calle, int CP, String localidad, String provincia, int numero) {
        this.calle = calle;
        this.CP = CP;
        this.localidad = localidad;
        this.provincia = provincia;
        this.numero = numero;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getCP() {
        return CP;
    }

    public void setCP(int CP) {
        this.CP = CP;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Direccion{" +
                "calle='" + calle + '\'' +
                ", CP=" + CP +
                ", localidad='" + localidad + '\'' +
                ", provincia='" + provincia + '\'' +
                ", numero=" + numero +
                '}';
    }

    public void setDireccion(String nuevaDireccion) {
        //Creamos un array de Strings con los datos de la nueva dirección
        String[] direccion = nuevaDireccion.split(",");
        //Asignamos los valores del array a los atributos de la clase
        this.calle = direccion[0];
        this.CP = Integer.parseInt(direccion[1]);
        this.localidad = direccion[2];
        this.provincia = direccion[3];
        this.numero = Integer.parseInt(direccion[4]);
    }
}
