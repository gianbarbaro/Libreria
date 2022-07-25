/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreriajpa.servicios;

import java.util.List;
import libreriajpa.entidades.Autor;
import libreriajpa.entidades.Editorial;
import libreriajpa.entidades.Libro;
import libreriajpa.persistencia.LibroDAO;

/**
 *
 * @author Usuario
 */
public class LibroService {
    
    private final LibroDAO dao;

    public LibroService() {
        dao = new LibroDAO();
    }
    
    public Libro guardar(String titulo, Autor autor, Editorial editorial, Long isbn, Integer anio, 
            Integer ejemplares, Integer ejemplaresPrestados, Integer ejemplaresRestantes) throws Exception {
        
        Libro libro = null;
        try {
            validar(titulo, autor, editorial, isbn, anio, ejemplares, ejemplaresPrestados, ejemplaresRestantes);
            libro = new Libro();
            
            libro.setTitulo(titulo);
            libro.setAutor(autor);
            libro.setEditorial(editorial);
            libro.setIsbn(isbn);
            libro.setAnio(anio);
            libro.setEjemplares(ejemplares);
            libro.setEjemplaresPrestados(ejemplaresPrestados);
            libro.setEjemplaresRestantes(ejemplaresRestantes);
            libro.setAlta(true);
            
            dao.guardar(libro);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return libro;
    }
    
    public Libro editar(Libro libro) throws Exception {
        if (libro == null) {
            throw new Exception("Debe ingresar el libro que desea editar"); 
        }
        return dao.editar(libro);
    }
   
    public void eliminar(Libro libro) throws Exception {
        if (libro == null) {
            throw new Exception("Debe ingresar el libro que desea eliminar"); 
        }
        dao.eliminar(libro);
    }
    
    public Libro buscarPorISBN(Long isbn) throws Exception {
        if (isbn == null || isbn < 0) {
            throw new Exception("Debe ingresar el código ISBN del libro");
        }
        return dao.buscarPorISBN(isbn);
    }
    
    public Libro buscarPorTitulo(String titulo) throws Exception {
        if (titulo.trim().isEmpty()) {
            throw new Exception("Debe ingresar un título");
        }
        return dao.buscarPorTitulo(titulo);
    }
    
    public List<Libro> buscarPorAutor(String nombre) throws Exception {
        if (nombre.trim().isEmpty()) {
            throw new Exception("Debe ingresar el nombre del autor");
        }
        return dao.buscarPorAutor(nombre);
    }
    
    public List<Libro> buscarPorEditorial(String nombre) throws Exception {
        if (nombre.trim().isEmpty()) {
            throw new Exception("Debe ingresar el nombre de la editorial");
        }
        return dao.buscarPorEditorial(nombre);
    }
    
    public List<Libro> listarTodos() throws Exception {
        try {
            return dao.listarTodos();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public void validar(String titulo, Autor autor, Editorial editorial, Long isbn, Integer anio, 
            Integer ejemplares, Integer ejemplaresPrestados, Integer ejemplaresRestantes) throws Exception {
        
        if (isbn == null || isbn < 0) {
            throw new Exception("Debe ingresar el código ISBN del libro");
        }
        if (titulo.trim().isEmpty()) {
            throw new Exception("Debe ingresar un título");
        }
        if (anio == null || anio < 1) {
            throw new Exception("El año ingresado es inválido");
        }
        if (ejemplares == null || ejemplares < 0) {
            throw new Exception("Debe indicar la cantidad de ejemplares disponible");
        }
        if (ejemplaresPrestados == null || ejemplaresPrestados < 0) {
            throw new Exception("Debe indicar la cantidad de ejemplares prestados");
        }
        if (ejemplaresRestantes == null || ejemplaresRestantes < 0) {
            throw new Exception("Debe indicar la cantidad de ejemplares restantes");
        }
        if (autor == null) {
            throw new Exception("Debe ingresar el autor del libro");
        }
        if (editorial == null) {
            throw new Exception("Debe ingresar la editorial del libro");
        }
    }
    
}
