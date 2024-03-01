package org.example.actividadSwagger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.sql.Date;

public interface CocheRepository extends JpaRepository<Coche, Integer> {

    @Query("SELECT c FROM Coche c JOIN c.conductor con WHERE con.anyosCarnet > 10")
    List<Coche> findCochesConConductorConMasDe10AnosDeCarnet();

    @Query("SELECT c FROM Coche c JOIN c.conductor con WHERE con.fechaNacimiento > :fechaNacimiento")
    List<Coche> findCochesConConductorMenorDe25Anos(@Param("fechaNacimiento") Date fechaNacimiento);

    @Query("SELECT c FROM Coche c WHERE c.id = :id")
    Coche findCocheById(@Param("id") Integer id);

    //Info de los coches conducidos por un conductor específico
    @Query("SELECT c FROM Coche c JOIN c.conductor con WHERE con.id = :idConductor")
    List<Coche> findCochesConConductor(@Param("idConductor") Integer idConductor);


}
