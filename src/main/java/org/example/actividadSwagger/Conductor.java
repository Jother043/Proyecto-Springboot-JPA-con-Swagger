package org.example.actividadSwagger;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

@Entity
public class Conductor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellidos;

    @Column(nullable = false)
    private Integer anyosCarnet;

    @Column(nullable = false)
    private Date fechaNacimiento;

    @Embedded
    private Direccion direccion;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    @JoinTable(
            name = "conductor_conduce_coche",
            joinColumns = @JoinColumn(name = "conductor_id"),
            inverseJoinColumns = @JoinColumn(name = "coche_id"))
    private List<Coche> coches;

    public Conductor() {
        this.coches = new ArrayList<>();
    }

    public Conductor(String nombre, String apellidos, Integer anyosCarnet, Date fechaNacimiento, Direccion direccion) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.anyosCarnet = anyosCarnet;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.coches = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Integer getAnyosCarnet() {
        return anyosCarnet;
    }

    public void setAnyosCarnet(Integer anyosCarnet) {
        this.anyosCarnet = anyosCarnet;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public List<Coche> getCoches() {
        return coches;
    }

    public void setCoches(List<Coche> coches) {
        this.coches = coches;
    }

    @Override
    public String toString() {
        return "Conductor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", anyosCarnet=" + anyosCarnet +
                ", fechaNacimiento=" + fechaNacimiento +
                ", direccion=" + direccion +
                '}';
    }
}