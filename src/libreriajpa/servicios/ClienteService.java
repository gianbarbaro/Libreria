/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreriajpa.servicios;

import java.util.List;
import libreriajpa.entidades.Cliente;
import libreriajpa.persistencia.ClienteDAO;

/**
 *
 * @author Usuario
 */
public class ClienteService {
    
    private final ClienteDAO dao;

    public ClienteService() {
        dao = new ClienteDAO();
    }
    
    public Cliente guardar(String nom, String apellido, Long dni, String tel) {
        
        Cliente cliente = null;
        try {
            validar(nom, apellido, dni, tel);
            cliente = new Cliente();
            
            cliente.setNombre(nom);
            cliente.setApellido(apellido);
            cliente.setDNI(dni);
            cliente.setTelefono(tel);
            
            dao.guardar(cliente);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return cliente;
    }
    
    public Cliente editar(Cliente cliente) throws Exception {
        if (cliente == null) {
            throw new Exception("Debe indicar el cliente al que desea modificar sus datos"); 
        }
        return dao.editar(cliente);
    }
    
    public void eliminar(Cliente cliente) throws Exception {
        if (cliente == null) {
            throw new Exception("Debe indicar el cliente que desea eliminar"); 
        }
        dao.eliminar(cliente);
    }
    
    public List<Cliente> listarTodos() {
        try {
            return dao.listarTodos();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public Cliente buscarPorID(String id) throws Exception {
        if (id.trim().isEmpty()) {
            throw new Exception("Debe ingresar el ID del cliente");
        }
        return dao.buscarPorID(id);
    }
    
    public void validar(String nom, String apellido, Long dni, String tel) throws Exception {
        
        if (nom.trim().isEmpty()) {
            throw new Exception("Debe indicar el nombre del cliente");
        }
        if (apellido.trim().isEmpty()) {
            throw new Exception("Debe indicar el apellido del cliente");
        }
        if (dni == null || dni < 1) {
            throw new Exception("Debe ingresar un DNI válido");
        }
        if (tel.trim().isEmpty()) {
            throw new Exception("Debe indicar el numero de telefeno correspondiente");
        }
    }
}
