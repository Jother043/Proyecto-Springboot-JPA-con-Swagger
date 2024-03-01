package org.example.actividadSwagger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/concesionario")
public class ConcesionarioController {

    @Autowired
    private final CocheRepository repository;

    @Autowired
    private final ConductorRepository repositoryConductor;

    /**
     * Constructor
     *
     * @param repository
     * @param repositoryConductor
     */
    public ConcesionarioController(CocheRepository repository, ConductorRepository repositoryConductor) {
        this.repository = repository;
        this.repositoryConductor = repositoryConductor;
    }

    //Info de todos los conductores
    @GetMapping("/conductores")
    public List<Conductor> allConductores() {
        return repositoryConductor.findAll();
    }

    //Info de todos los coches
    @GetMapping("/coches")
    public List<Coche> allCoches() {
        return repository.findAll();
    }

    //Info de los coches conducidos por un conductor específico
    @GetMapping("/coches/{idConductor}")
    public List<Coche> cochesConductor(@PathVariable int idConductor) {
        return repository.findCochesConConductor(idConductor);
    }

    //Info de los conductores de un coche determinado
    @GetMapping("/conductores/{idCoche}")
    public Conductor conductoresCoche(@PathVariable int idCoche) {
        return repositoryConductor.findByCoche(idCoche);
    }

    //Info de los coches cuyo conductor tenga más de 10 años de carnet
    @GetMapping("/coches/mas10AniosCarnet")
    public List<Coche> cochesConductorMas10() {
        return repository.findCochesConConductorConMasDe10AnosDeCarnet();
    }

    //Info de los coches cuyo conductor tenga menos de 25 años
    @GetMapping("/coches/menos25Anios")
    public List<Coche> cochesConductorMenos25() {
        return repository.findCochesConConductorMenorDe25Anos(Date.valueOf("1996-01-01"));
    }

    //Info de los conductores cuyo nombre empieza por J, apellido por E y tienen menos de 5 años de carnet
    @GetMapping("/conductores/nombreJApellidoE")
    public List<Conductor> conductoresNombreJApellidoE() {
        return repositoryConductor.findDriversWithFirstNameSurnameAndSpecificExperienc();
    }

    //Info de los conductores que tienen entre 3 y 6 años de carnet
    @GetMapping("/conductores/entre3Y6AniosCarnet")
    public List<Conductor> conductoresEntre3Y6AniosCarnet() {
        return repositoryConductor.findDriversWithExperienceBetween3Y6Years();
    }

    //Actualizar la dirección de un conductor
    @PutMapping("/conductores/{idConductor}")
    public Conductor actualizarDireccionConductor(@PathVariable int idConductor, @RequestBody Conductor conductor) {
        Conductor conductorActual = repositoryConductor.findConductorById(idConductor);
        conductorActual.setDireccion(conductor.getDireccion());
        return repositoryConductor.save(conductorActual);
    }

    //Eliminar un coche
    @DeleteMapping("/coches/{idCoche}")
    public void eliminarCoche(@PathVariable int idCoche) {
        repository.deleteById(idCoche);
    }
}
