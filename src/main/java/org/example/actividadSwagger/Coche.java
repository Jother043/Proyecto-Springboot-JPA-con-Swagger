package org.example.actividadSwagger;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Coche {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String matricula;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private String modelo;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    @JoinTable(
            name = "conductor_conduce_coche",
            joinColumns = @JoinColumn(name = "coche_id"),
            inverseJoinColumns = @JoinColumn(name = "conductor_id"))
    private List<Conductor> conductor;

    public Coche() {
        this.conductor = new ArrayList<>(); // Inicializar la lista de conductores
    }

    public Coche(String matricula, String marca, String modelo) {
        this.id = id;
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.conductor = new ArrayList<>(); // Inicializar la lista de conductores
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        if(matricula.length() > 7) {
            throw new IllegalArgumentException("La matrícula no puede tener más de 7 caracteres");
        }
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        if(marca.length() > 45) {
            throw new IllegalArgumentException("La marca no puede tener más de 45 caracteres");
        }
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        if(modelo.length() > 45) {
            throw new IllegalArgumentException("El modelo no puede tener más de 45 caracteres");
        }
    }

    public List<Conductor> getConductor() {
        return conductor;
    }

    public void setConductor(List<Conductor> conductor) {
        this.conductor = conductor;
    }

    @Override
    public String toString() {
        return "Coche{" +
                "id=" + id +
                ", matricula='" + matricula + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                '}';
    }
}
