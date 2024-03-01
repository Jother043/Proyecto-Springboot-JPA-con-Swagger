package org.example.actividadSwagger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConductorRepository extends JpaRepository<Conductor, Integer> {

    //Mostar los conductores que tienen más de 10 años de carnet.
    @Query("SELECT c FROM Conductor c WHERE c.anyosCarnet > 10")
    List<Conductor> findDriversWithMoreThan10YearsOfLicense();

    @Query("SELECT c FROM Conductor c WHERE c.nombre LIKE 'J%' AND c.apellidos LIKE 'E%' AND c.anyosCarnet < 5")
    List<Conductor> findDriversWithFirstNameSurnameAndSpecificExperienc();

    @Query("SELECT c FROM Conductor c WHERE c.anyosCarnet BETWEEN 3 AND 6")
    List<Conductor> findDriversWithExperienceBetween3Y6Years();

    @Query("SELECT c FROM Conductor c WHERE c.id = :id")
    Conductor findConductorById(@Param("id") Integer id);

    //Mostar los conductores que tienen menos de 25 años.
    @Query("SELECT c FROM Conductor c WHERE c.fechaNacimiento > '1996-01-01'")
    List<Conductor> findDriversUnder25YearsOld();

    @Query("SELECT c FROM Conductor c JOIN c.coches co WHERE co.id = :idCoche")
    Conductor findByCoche(int idCoche);
}