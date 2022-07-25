/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreriajpa.servicios;

import java.util.List;
import libreriajpa.entidades.Autor;
import libreriajpa.persistencia.AutorDAO;

/**
 *
 * @author Usuario
 */
public class AutorService {
    
    private final AutorDAO dao;

    public AutorService() {
        dao = new AutorDAO();
    }
    
    public Autor guardar(String nombre) throws Exception {
        if (nombre.trim().isEmpty()) {
            throw new Exception("No se puede cargar el autor en el sistema, debe indicar su nombre");
        }
        
        Autor autor = new Autor();
        
        autor.setNombre(nombre);
        autor.setAlta(true);
        
        dao.guardar(autor);
        
        return autor;
    }
    
    public Autor editar(Autor autor) throws Exception {
        if (autor == null) {
            throw new Exception("Debe ingresar el autor que desea editar"); 
        }
        return dao.editar(autor);
    }
   
    public void eliminar(Autor autor) throws Exception {
        if (autor == null) {
            throw new Exception("Debe ingresar el autor que desea eliminar"); 
        }
        dao.eliminar(autor);
    }
    
    public Autor buscarPorNombre(String nombre) throws Exception {
        if (nombre.trim().isEmpty()) {
            throw new Exception("Debe ingresar el nombre del autor que desea buscar");
        }
        return dao.buscarPorNombre(nombre);
    }
    
    public List<Autor> listarTodos() throws Exception {
        try {
            return dao.listarTodos();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public Autor buscarPorID(String id) throws Exception {
        if (id == null) {
            throw new Exception("Debe ingresar el ID correspondiente");
        }
        return dao.buscarPorID(id);
    }
}
