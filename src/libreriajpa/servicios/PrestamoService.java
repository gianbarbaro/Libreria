/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreriajpa.servicios;

import java.util.Date;
import java.util.List;
import libreriajpa.entidades.Cliente;
import libreriajpa.entidades.Libro;
import libreriajpa.entidades.Prestamo;
import libreriajpa.persistencia.PrestamoDAO;

/**
 *
 * @author Usuario
 */
public class PrestamoService {
    
    private final PrestamoDAO dao;

    public PrestamoService() {
        dao = new PrestamoDAO();
    }
    
    public Prestamo guardar(Cliente cliente, Libro libro, Date fechaPrestamo, Date fechaDevolucion) {
        
        Prestamo prestamo = null;
        try {
            validar(cliente, libro, fechaPrestamo, fechaDevolucion);
            prestamo = new Prestamo();
            
            prestamo.setCliente(cliente);
            prestamo.setLibro(libro);
            prestamo.setFechaPrestamo(fechaPrestamo);
            prestamo.setFechaDevolucion(fechaDevolucion);
            
            dao.guardar(prestamo);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return prestamo;
    }
    
    public Prestamo editar(Prestamo prestamo) throws Exception {
        if (prestamo == null) {
            throw new Exception("Debe seleccionar el prestamo que desea modificar"); 
        }
        return dao.editar(prestamo);
    }
    
    public void eliminar(Prestamo prestamo) throws Exception {
        if (prestamo == null) {
            throw new Exception("Debe ingresar el prestamo que desea eliminar"); 
        }
        dao.eliminar(prestamo);
    }
    
    public Prestamo buscarPorCliente(String id) throws Exception {
        if (id.trim().isEmpty()) {
            throw new Exception("Debe indicar el ID del cliente");
        }
        return dao.buscarPorCliente(id);
    }
    
    public List<Prestamo> listarTodos() {
        try {
            return dao.listarTodos();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public void validar(Cliente cliente, Libro libro, Date fechaPrestamo, Date fechaDevolucion) throws Exception {
        
        if (cliente == null) {
            throw new Exception("Debe indicar el cliente al que quiere realizar el prestamo");
        }
        if (libro == null) {
            throw new Exception("Debe indicar el libro que se quiere prestar");
        }
        if (fechaPrestamo == null) {
            throw new Exception("Debe indicar la fecha del prestamo");
        }
        if (fechaDevolucion == null) {
            throw new Exception("Debe indicar la fecha de devolucion del libro");
        }
    }
}
