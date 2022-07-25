/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreriajpa.servicios;

import java.util.List;
import libreriajpa.entidades.Editorial;
import libreriajpa.persistencia.EditorialDAO;

/**
 *
 * @author Usuario
 */
public class EditorialService {
    
    private final EditorialDAO dao;

    public EditorialService() {
        dao = new EditorialDAO();
    }
    
    public Editorial guardar(String nombre) throws Exception {
        if (nombre.trim().isEmpty()) {
            throw new Exception("No se pudo cargar la editorial en el sistema, debe indicar su nombre");
        }
        
        Editorial editorial = new Editorial();
        
        editorial.setNombre(nombre);
        editorial.setAlta(true);
        
        dao.guardar(editorial);
        
        return editorial;
    }
    
    public Editorial editar(Editorial editorial) throws Exception {
        if (editorial == null) {
            throw new Exception("Debe ingresar la editorial que desea editar"); 
        }
        return dao.editar(editorial);
    }
   
    public void eliminar(Editorial editorial) throws Exception {
        if (editorial == null) {
            throw new Exception("Debe ingresar la editorial que desea eliminar"); 
        }
        dao.eliminar(editorial);
    }
    
    public List<Editorial> listarTodos() throws Exception {
        try {
            return dao.listarTodos();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public Editorial buscarPorID(String id) throws Exception {
        if (id == null) {
            throw new Exception("Debe ingresar el ID correspondiente");
        }
        return dao.buscarPorID(id);
    }
    
    public Editorial buscarPorNombre(String nombre) throws Exception {
        if (nombre.trim().isEmpty()) {
            throw new Exception("Debe ingresar el nombre de la editorial que desea buscar");
        }
        return dao.buscarPorNombre(nombre);
    }
}
