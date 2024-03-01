package org.example.actividadSwagger;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Date;
import java.util.Scanner;
import java.util.List;

@SpringBootApplication
public class ActividadSwaggerApplication implements CommandLineRunner {

    private Scanner teclado = new Scanner(System.in);

    private final CocheRepository repository;
    private final ConductorRepository repositoryConductor;

    public ActividadSwaggerApplication(CocheRepository repository, ConductorRepository repositoryConductor) {
        this.repository = repository;
        this.repositoryConductor = repositoryConductor;
    }

    public static void main(String[] args) {
        SpringApplication.run(ActividadSwaggerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if(repository.count() == 0) {
            insertarDatos();
        }
        while (true) {
            System.out.println("Menú de opciones:");
            System.out.println("1. Mostrar toda la info de todos los conductores");
            System.out.println("2. Mostrar toda la info de todos los coches");
            System.out.println("3. Mostrar toda la info de los coches conducidos por un conductor específico");
            System.out.println("4. Mostrar toda la info de los conductores de un coche determinado");
            System.out.println("5. Mostrar toda la info de los coches cuyo conductor tenga más de 10 años de carnet");
            System.out.println("6. Mostrar toda la info de los coches cuyo conductor tenga menos de 25 años");
            System.out.println("7. Mostrar toda la info de los conductores cuyo nombre empieza por J, apellido por E y tienen menos de 5 años de carnet");
            System.out.println("8. Mostrar toda la info de los conductores que tienen entre 3 y 6 años de carnet");
            System.out.println("9. Actualizar la dirección de un conductor");
            System.out.println("10. Eliminar un coche");
            System.out.println("0. Salir");

            System.out.println("Seleccione una opción: ");
            int opcion = teclado.nextInt();

            switch (opcion) {
                case 1:
                    mostrarInfoConductores();
                    break;
                case 2:
                    mostrarInfoCoches();
                    break;
                case 3:
                    System.out.print("Ingrese el ID del conductor: ");
                    int idConductor = teclado.nextInt();
                    mostrarInfoCochesConductor(idConductor);
                    break;
                case 4:
                    System.out.print("Ingrese el ID del coche: ");
                    int idCoche = teclado.nextInt();
                    mostrarInfoConductoresCoche(idCoche);
                    break;
                case 5:
                    showCarsWithLicenseMore10Years();
                    break;
                case 6:
                    showCarsDriverUnder25Years();
                    break;
                case 7:
                    showDriversFirstNameSurnameLicense();
                    break;
                case 8:
                    showDriversBetween3and6YearsLicense();
                    break;
                case 9:
                    System.out.print("Ingrese el ID del conductor a actualizar: ");
                    int idConductorActualizar = teclado.nextInt();
                    System.out.print("Ingrese la nueva dirección: ");
                    String nuevaDireccion = teclado.next();
                    actualizarDireccionConductor(idConductorActualizar, nuevaDireccion);
                    break;
                case 10:
                    System.out.print("Ingrese el ID del coche a eliminar: ");
                    int idCocheEliminar = teclado.nextInt();
                    eliminarCoche(idCocheEliminar);
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    System.exit(0);
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    private void eliminarCoche(int idCocheEliminar) {
        repository.deleteById(idCocheEliminar);
    }

    private void actualizarDireccionConductor(int idConductorActualizar, String nuevaDireccion) {
        repositoryConductor.findById(idConductorActualizar).ifPresent(conductor -> {
            Direccion direccion = conductor.getDireccion();
            direccion.setDireccion(nuevaDireccion);
            repositoryConductor.save(conductor);
        });
    }

    private void showDriversBetween3and6YearsLicense() {
        repositoryConductor.findDriversWithExperienceBetween3Y6Years().forEach(System.out::println);
    }

    private void showDriversFirstNameSurnameLicense() {
        repositoryConductor.findDriversWithFirstNameSurnameAndSpecificExperienc().forEach(System.out::println);
    }

    private void showCarsDriverUnder25Years() {
        repositoryConductor.findDriversUnder25YearsOld().forEach(System.out::println);
    }

    private void showCarsWithLicenseMore10Years() {
        repositoryConductor.findDriversWithMoreThan10YearsOfLicense().forEach(System.out::println);
    }

    private void mostrarInfoConductoresCoche(int idCoche) {
        repository.findById(idCoche).ifPresent(coche -> {
            List<Conductor> conductores = coche.getConductor();
            conductores.forEach(System.out::println);
        });
    }

    private void mostrarInfoCochesConductor(int idConductor) {
        repositoryConductor.findById(idConductor).ifPresent(conductor -> {
            List<Coche> coches = conductor.getCoches();
            coches.forEach(System.out::println);
        });
    }

    private void mostrarInfoCoches() {
        repository.findAll().forEach(System.out::println);
    }

    private void mostrarInfoConductores() {
        repositoryConductor.findAll().forEach(System.out::println);
    }

    private void insertarDatos() {
        //Creamos 4 conductores
        Direccion direccion1 = new Direccion("Calle 123", 28080, "Ciudad 1", "Provincia 1", 1);
        Conductor conductor1 = new Conductor("Juan", "Perez", 10, new Date(1990, 1, 1), direccion1);
        Direccion direccion2 = new Direccion("Calle 456", 28080, "Ciudad 2", "Provincia 2", 2);
        Conductor conductor2 = new Conductor("Pedro", "García", 15, new Date(1985, 1, 1), direccion2);
        Direccion direccion3 = new Direccion("Calle 789", 28080, "Ciudad 3", "Provincia 3", 3);
        Conductor conductor3 = new Conductor("Jose", "Lopez", 20, new Date(1980, 1, 1), direccion3);
        Direccion direccion4 = new Direccion("Calle 012", 28080, "Ciudad 4", "Provincia 4", 4);
        Conductor conductor4 = new Conductor("Javier", "Gonzalez", 25, new Date(1975, 1, 1), direccion4);
        Direccion direccion5 = new Direccion("Calle 345", 28080, "Ciudad 5", "Provincia 5", 5);
        Conductor conductor5 = new Conductor("Jorge", "Esteban", 4, new Date(1997, 1, 1), direccion5);


        //Creamos 10 coches
        Coche coche1 = new Coche("1234ABC", "Seat", "Ibiza");
        Coche coche2 = new Coche("2345BCD", "Renault", "Clio");
        Coche coche3 = new Coche("3456CDE", "Ford", "Focus");
        Coche coche4 = new Coche("4567DEF", "Opel", "Corsa");
        Coche coche5 = new Coche("5678EFG", "Seat", "Leon");
        Coche coche6 = new Coche("6789FGH", "Renault", "Megane");
        Coche coche7 = new Coche("7890GHI", "Ford", "Fiesta");
        Coche coche8 = new Coche("8901HIJ", "Opel", "Astra");
        Coche coche9 = new Coche("9012IJK", "Seat", "Toledo");
        Coche coche10 = new Coche("0123JKL", "Renault", "Laguna");

        // Guardar las entidades Coche en la base de datos
        repository.save(coche1);
        repository.save(coche2);
        repository.save(coche3);
        repository.save(coche4);
        repository.save(coche5);
        repository.save(coche6);
        repository.save(coche7);
        repository.save(coche8);
        repository.save(coche9);
        repository.save(coche10);

        // Establecer las relaciones entre los coches y los conductores
        conductor1.getCoches().add(coche1);
        conductor1.getCoches().add(coche2);
        conductor2.getCoches().add(coche3);
        conductor2.getCoches().add(coche4);
        conductor3.getCoches().add(coche5);
        conductor3.getCoches().add(coche6);
        conductor4.getCoches().add(coche7);
        conductor4.getCoches().add(coche8);
        conductor5.getCoches().add(coche9);

        coche1.getConductor().add(conductor1);
        coche2.getConductor().add(conductor1);
        coche3.getConductor().add(conductor2);
        coche4.getConductor().add(conductor2);
        coche5.getConductor().add(conductor3);
        coche6.getConductor().add(conductor3);
        coche7.getConductor().add(conductor4);
        coche8.getConductor().add(conductor4);
        coche9.getConductor().add(conductor5);

        // Guardar las entidades Conductor en la base de datos
        repositoryConductor.save(conductor1);
        repositoryConductor.save(conductor2);
        repositoryConductor.save(conductor3);
        repositoryConductor.save(conductor4);
        repositoryConductor.save(conductor5);
    }
}
