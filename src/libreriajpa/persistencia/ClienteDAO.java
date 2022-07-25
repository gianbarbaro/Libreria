/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreriajpa.persistencia;

import java.util.List;
import libreriajpa.entidades.Cliente;

/**
 *
 * @author Usuario
 */
public final class ClienteDAO extends DAO{
    
    public void guardar(Cliente objeto) {
        conectar();
        em.getTransaction().begin();
        em.persist(objeto);
        em.getTransaction().commit();
        desconectar();
    }
    
    public Cliente editar(Cliente objeto) {
        conectar();
        em.getTransaction().begin();
        Cliente c = em.merge(objeto);
        em.getTransaction().commit();
        desconectar();
        return c;
    }
    
    public void eliminar(Cliente objeto) {
        conectar();
        em.getTransaction().begin();
        em.remove(objeto);
        em.getTransaction().commit();
        desconectar();
    }
    
    public List<Cliente> listarTodos() {
        conectar();
        List<Cliente> clientes = em.createQuery("SELECT c FROM Cliente c").getResultList();
        desconectar();
        return clientes;
    }
    
    public Cliente buscarPorID(String id) {
        conectar();
        Cliente cliente = em.find(Cliente.class, id);
        desconectar();
        return cliente;
    }
}
