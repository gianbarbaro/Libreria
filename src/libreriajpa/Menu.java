/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreriajpa;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import libreriajpa.entidades.Autor;
import libreriajpa.entidades.Cliente;
import libreriajpa.entidades.Editorial;
import libreriajpa.entidades.Libro;
import libreriajpa.servicios.AutorService;
import libreriajpa.servicios.ClienteService;
import libreriajpa.servicios.EditorialService;
import libreriajpa.servicios.LibroService;
import libreriajpa.servicios.PrestamoService;

/**
 *
 * @author Usuario
 */
public class Menu {

    private final Scanner leer;
    private final AutorService autorServ;
    private final EditorialService editorialServ;
    private final LibroService libroServ;
    private final ClienteService clienteServ;
    private final PrestamoService prestamoServ;

    public Menu() {
        leer = new Scanner(System.in).useDelimiter("\n");
        autorServ = new AutorService();
        editorialServ = new EditorialService();
        libroServ = new LibroService();
        clienteServ = new ClienteService();
        prestamoServ = new PrestamoService();
    }

    public void menu() throws Exception {

        int op, anio, ejemplares, ejemplaresPrestados, ejemplaresRestantes, dd, mm, aa;
        long isbn, dni;
        String nom, apellido, tel, titulo, id;
        Autor autor = null;
        Editorial editorial = null;
        Libro libro = null;
        Cliente cliente = null;
        Date prestamo, devolucion;

        System.out.println("--------------- BIENVENIDOS A TÚ LIBRERIA VIRTUAL ---------------");
        do {
            System.out.println(" ");
            opciones();
            op = leer.nextInt();
            System.out.println(" ");
            switch (op) {
                case 1:
                    System.out.println("Ingrese el nombre completo del autor");
                    nom = leer.next();
                    System.out.println(autorServ.guardar(nom));
                    System.out.println("El autor se cargó en el sistema con éxito :)");
                    break;
                case 2:
                    System.out.println("Ingrese el nombre de la editorial");
                    nom = leer.next();
                    System.out.println(editorialServ.guardar(nom));
                    System.out.println("La editorial se cargó en el sistema con éxito :)");
                    break;
                case 3:
                    verificarLista(autorServ.listarTodos());
                    verificarLista(editorialServ.listarTodos());

                    System.out.println("Ingrese los siguientes datos");
                    System.out.print("titulo: ");
                    titulo = leer.next();

                    System.out.println("--------- Autores registrados ---------");
                    autorServ.listarTodos();
                    System.out.print("indique el autor (ID): ");
                    id = leer.next();
                    if (autorServ.buscarPorID(id) == null) {
                        System.out.println("No se encontró ningun resultado");
                        break;
                    } else {
                        autor = autorServ.buscarPorID(id);
                    }

                    System.out.println("------- Editoriales registradas -------");
                    editorialServ.listarTodos();
                    System.out.print("indique la editorial (ID): ");
                    id = leer.next();
                    if (editorialServ.buscarPorID(id) == null) {
                        System.out.println("No se encontró ningun resultado");
                        break;
                    } else {
                        editorial = editorialServ.buscarPorID(id);
                    }

                    System.out.print("ISBN: ");
                    isbn = leer.nextLong();
                    System.out.print("año: ");
                    anio = leer.nextInt();
                    System.out.print("ejemplares: ");
                    ejemplares = leer.nextInt();
                    System.out.print("ejemplares prestados: ");
                    ejemplaresPrestados = leer.nextInt();
                    System.out.println("calculando ejemplares restantes...");
                    ejemplaresRestantes = ejemplares - ejemplaresPrestados;

                    System.out.println(libroServ.guardar(titulo, autor, editorial, isbn, anio,
                            ejemplares, ejemplaresPrestados, ejemplaresRestantes));
                    System.out.println("El libro se cargó en el sistema con éxito");
                    break;
                case 4:
                    verificarLista(autorServ.listarTodos());

                    System.out.println("--------- Autores registrados ---------");
                    autorServ.listarTodos();
                    System.out.print("ID del autor a editar: ");
                    id = leer.next();
                    if (autorServ.buscarPorID(id) == null) {
                        System.out.println("No se encontró ningun resultado");
                        break;
                    } else {
                        autor = autorServ.buscarPorID(id);
                    }

                    System.out.println("Actualización de datos...");
                    System.out.print("nuevo nombre: ");
                    nom = leer.next();
                    autor.setNombre(nom);

                    System.out.println(autorServ.editar(autor));
                    System.out.println("Datos actualizados correctamente!");
                    break;
                case 5:
                    verificarLista(editorialServ.listarTodos());

                    System.out.println("------- Editoriales registrados -------");
                    autorServ.listarTodos();
                    System.out.print("ID de la editorial a editar: ");
                    id = leer.next();
                    if (editorialServ.buscarPorID(id) == null) {
                        System.out.println("No se encontró ningun resultado");
                        break;
                    } else {
                        editorial = editorialServ.buscarPorID(id);
                    }

                    System.out.println("Actualización de datos...");
                    System.out.print("nuevo nombre: ");
                    nom = leer.next();
                    editorial.setNombre(nom);

                    System.out.println(editorialServ.editar(editorial));
                    System.out.println("Datos actualizados correctamente!");
                    break;
                case 6:
                    verificarLista(libroServ.listarTodos());

                    System.out.println("---------- Libros registrados ---------");
                    autorServ.listarTodos();
                    System.out.print("ISBN del libro a editar: ");
                    isbn = leer.nextLong();
                    if (libroServ.buscarPorISBN(isbn) == null) {
                        System.out.println("No se encontró ningun resultado");
                        break;
                    } else {
                        libro = libroServ.buscarPorISBN(isbn);
                    }

                    do {
                        System.out.println("Actualización de datos...");
                        System.out.println(" ");

                        menuEditarLibro();
                        op = leer.nextInt();

                        switch (op) {
                            case 1:
                                System.out.print("nuevo titulo: ");
                                titulo = leer.next();
                                libro.setTitulo(titulo);
                                break;
                            case 2:
                                System.out.println("nombre del nuevo autor: ");
                                nom = leer.next();
                                if (autorServ.buscarPorNombre(nom) == null) {
                                    System.out.println("El autor no existe. Primero debe cargarlo en el sistema");
                                } else {
                                    libro.setAutor(autorServ.buscarPorNombre(nom));
                                }
                                break;
                            case 3:
                                System.out.println("nombre de la nueva editorial: ");
                                nom = leer.next();
                                if (editorialServ.buscarPorNombre(nom) == null) {
                                    System.out.println("La editorial no existe. Primero debe cargarla en el sistema");
                                } else {
                                    libro.setEditorial(editorialServ.buscarPorNombre(nom));
                                }
                                break;
                            case 4:
                                System.out.print("nuevo ISBN: ");
                                isbn = leer.nextLong();
                                libro.setIsbn(isbn);
                                break;
                            case 5:
                                System.out.print("nuevo año: ");
                                anio = leer.nextInt();
                                libro.setAnio(anio);
                                break;
                            case 6:
                                System.out.print("actualizar ejemplares: ");
                                ejemplares = leer.nextInt();
                                libro.setEjemplares(ejemplares);
                                break;
                            case 7:
                                System.out.print("actualizar ejemplares prestados: ");
                                ejemplaresPrestados = leer.nextInt();
                                libro.setEjemplaresPrestados(ejemplaresPrestados);
                                break;
                            case 8:
                                System.out.print("actualizar ejemplares restantes: ");
                                ejemplaresRestantes = leer.nextInt();
                                libro.setEjemplaresRestantes(ejemplaresRestantes);
                                break;
                            default:
                                System.out.println(libroServ.editar(libro));
                                System.out.println("Datos actualizados correctamente!");
                        }
                    } while (op != 9);

                    break;
                case 7:
                    verificarLista(autorServ.listarTodos());

                    System.out.println("--------- Autores registrados ---------");
                    autorServ.listarTodos();
                    System.out.print("ID del autor a eliminar: ");
                    id = leer.next();
                    if (autorServ.buscarPorID(id) == null) {
                        System.out.println("No se encontró ningun resultado");
                    } else {
                        autorServ.eliminar(autorServ.buscarPorID(id));
                        System.out.println("Los datos se eliminaron correctamente");
                    }
                    break;
                case 8:
                    verificarLista(editorialServ.listarTodos());

                    System.out.println("------- Editoriales registrados -------");
                    autorServ.listarTodos();
                    System.out.print("ID de la editorial a eliminar: ");
                    id = leer.next();
                    if (editorialServ.buscarPorID(id) == null) {
                        System.out.println("No se encontró ningun resultado");
                    } else {
                        editorialServ.eliminar(editorialServ.buscarPorID(id));
                        System.out.println("Los datos se eliminaron correctamente");
                    }
                    break;
                case 9:
                    verificarLista(libroServ.listarTodos());

                    System.out.println("---------- Libros registrados ---------");
                    autorServ.listarTodos();
                    System.out.print("ISBN del libro a eliminar: ");
                    isbn = leer.nextLong();
                    if (libroServ.buscarPorISBN(isbn) == null) {
                        System.out.println("No se encontró ningun resultado");
                    } else {
                        libroServ.eliminar(libroServ.buscarPorISBN(isbn));
                        System.out.println("Los datos se eliminaron correctamente");
                    }
                    break;
                case 10:
                    verificarLista(autorServ.listarTodos());

                    System.out.print("Indique el nombre --->");
                    nom = leer.next();
                    if (autorServ.buscarPorNombre(nom) == null) {
                        System.out.println("No se encontró ningun resultado");
                    } else {
                        System.out.println(autorServ.buscarPorNombre(nom));
                    }
                    break;
                case 11:
                    verificarLista(libroServ.listarTodos());

                    System.out.print("Indique el ISBN --->");
                    isbn = leer.nextLong();
                    if (libroServ.buscarPorISBN(isbn) == null) {
                        System.out.println("No se encontró ningun resultado");
                    } else {
                        System.out.println(libroServ.buscarPorISBN(isbn));
                    }
                    break;
                case 12:
                    verificarLista(libroServ.listarTodos());

                    System.out.print("Indique el titulo --->");
                    titulo = leer.next();
                    if (libroServ.buscarPorTitulo(titulo) == null) {
                        System.out.println("No se encontró ningun resultado");
                    } else {
                        System.out.println(libroServ.buscarPorTitulo(titulo));
                    }
                    break;
                case 13:
                    verificarLista(libroServ.listarTodos());

                    System.out.print("Indique el nombre del autor --->");
                    nom = leer.next();
                    if (autorServ.buscarPorNombre(nom) == null) {
                        System.out.println("El autor que esta buscando no existe");
                    } else if (libroServ.buscarPorAutor(nom) == null) {
                        System.out.println("No se encontró ningun resultado");
                    } else {
                        mostrar(libroServ.buscarPorAutor(nom));
                    }
                    break;
                case 14:
                    verificarLista(libroServ.listarTodos());

                    System.out.print("Indique el nombre de la editorial --->");
                    nom = leer.next();
                    if (editorialServ.buscarPorNombre(nom) == null) {
                        System.out.println("La editorial que esta buscando no existe");
                    } else if (libroServ.buscarPorEditorial(nom) == null) {
                        System.out.println("No se encontró ningun resultado");
                    } else {
                        mostrar(libroServ.buscarPorEditorial(nom));
                    }
                    break;
                case 15:
                    verificarLista(clienteServ.listarTodos());
                    verificarLista(prestamoServ.listarTodos());
                    
                    System.out.print("Ingrese el ID del cliente ---> ");
                    id = leer.next();
                    cliente = clienteServ.buscarPorID(id);
                    if (cliente == null) {
                        System.out.println("No se encontraron resultados");
                    } else {
                        System.out.println("Prestamo actual de " + cliente.getNombre() + ":");
                        System.out.println(prestamoServ.buscarPorCliente(id));
                    }
                    break;
                case 16:
                    System.out.println("Ingrese los siguientes datos");
                    System.out.print("nombre: ");
                    nom = leer.next();
                    System.out.print("apellido: ");
                    apellido = leer.next();
                    System.out.print("DNI: ");
                    dni = leer.nextLong();
                    System.out.print("número de teléfono: ");
                    tel = leer.next();

                    System.out.println(clienteServ.guardar(nom, apellido, dni, tel));
                    System.out.println("El cliente se registró con éxito :)");
                    break;
                case 17:
                    verificarLista(clienteServ.listarTodos());
                    verificarLista(libroServ.listarTodos());

                    System.out.println("Ingrese los siguientes datos");
                    System.out.print("ID del cliente: ");
                    id = leer.next();
                    if (clienteServ.buscarPorID(id) == null) {
                        System.out.println("El cliente no se encuentra registrado en el sistema");
                    } else {
                        cliente = clienteServ.buscarPorID(id);
                    }

                    System.out.print("ISBN del libro: ");
                    isbn = leer.nextLong();
                    if (libroServ.buscarPorISBN(isbn) == null) {
                        System.out.println("No se encontró ningun resultado");
                    } else {
                        libro = libroServ.buscarPorISBN(isbn);
                    }
                    if (libro.getEjemplaresRestantes().equals(0)) {
                        System.out.println("No quedan ejemplares disponibles actualmente");
                        break;
                    } else {
                        libro.setEjemplaresPrestados(libro.getEjemplaresPrestados() + 1);
                        libro.setEjemplaresRestantes(libro.getEjemplaresRestantes() - 1);
                        libro = libroServ.editar(libro);
                    }

                    prestamo = new Date();
                    System.out.print("Fecha de devolución (dd/mm/aa): ");
                    dd = leer.nextInt();
                    mm = leer.nextInt();
                    aa = leer.nextInt();
                    devolucion = new Date(aa - 1900, mm - 1, dd);

                    System.out.println(prestamoServ.guardar(cliente, libro, prestamo, devolucion));
                    System.out.println("Prestamo realizado con éxito!");
                    break;
                case 18:
                    verificarLista(clienteServ.listarTodos());

                    System.out.println("--------- Clientes registrados --------");
                    mostrar(clienteServ.listarTodos());
                    break;
                case 19:
                    verificarLista(prestamoServ.listarTodos());

                    System.out.println("---------- Prestamos vigentes ---------");
                    mostrar(prestamoServ.listarTodos());
                    break;
                default:
                    System.out.println("Sesión cerrada correctamente");
            }
        } while (op != 20);
    }

    public void opciones() {
        System.out.println("¿Qué quiere hacer?");

        System.out.println("Dar de alta:");
        System.out.println("1- autor");
        System.out.println("2- editoral");
        System.out.println("3- libro");

        System.out.println("Editar los datos de un/a:");
        System.out.println("4- autor");
        System.out.println("5- editorial");
        System.out.println("6- libro");

        System.out.println("Dar de baja:");
        System.out.println("7- autor");
        System.out.println("8- editorial");
        System.out.println("9- libro");

        System.out.println("Buscar según:");
        System.out.println("10- autor por nombre");
        System.out.println("11- libro por ISBN");
        System.out.println("12- libro por titulo");
        System.out.println("13- libro/s por nombre de autor");
        System.out.println("14- libro/s por nombre de editorial");
        System.out.println("15- prestamo por cliente");

        System.out.println("Registrar en el sistema:");
        System.out.println("16- cliente");
        System.out.println("17- prestamo");

        System.out.println("Listar:");
        System.out.println("18- clientes");
        System.out.println("19- prestamos");

        System.out.println("20- Cerrar Sesión");
    }

    public void verificarLista(List lista) throws Exception {
        if (lista == null) {
            throw new Exception("Lo sentimos, la lista a la que desea acceder no existe en el sistema");
        }
    }

    public void mostrar(List lista) {
        lista.forEach((object) -> {
            System.out.println(object);
        });
    }

    public void menuEditarLibro() {

        System.out.println("Seleccione los datos que desea modificar");
        System.out.println("1- titulo");
        System.out.println("2- autor");
        System.out.println("3- editorial");
        System.out.println("4- ISBN");
        System.out.println("5- año");
        System.out.println("6- ejemplares");
        System.out.println("7- cantidad prestados");
        System.out.println("8- cantidad restantes");
        System.out.println("9- salir");
    }
}
