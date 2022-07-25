/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreriajpa.persistencia;

import java.util.List;
import libreriajpa.entidades.Editorial;

/**
 *
 * @author Usuario
 */
public final class EditorialDAO extends DAO {
    
    public void guardar(Editorial objeto) {
        conectar();
        em.getTransaction().begin();
        em.persist(objeto);
        em.getTransaction().commit();
        desconectar();
    }
    
    public Editorial editar(Editorial objeto) {
        conectar();
        em.getTransaction().begin();
        Editorial e = em.merge(objeto);
        em.getTransaction().commit();
        desconectar();
        return e;
    }
    
    public void eliminar(Editorial objeto) {
        conectar();
        em.getTransaction().begin();
        em.remove(objeto);
        em.getTransaction().commit();
        desconectar();
    }
    
    public List<Editorial> listarTodos() {
        conectar();
        List<Editorial> editoriales = em.createQuery("SELECT e FROM Editorial e").getResultList();
        desconectar();
        return editoriales;
    }
    
    public Editorial buscarPorID(String id) {
        conectar();
        Editorial editorial = em.find(Editorial.class, id);
        desconectar();
        return editorial;
    }
    
    public Editorial buscarPorNombre(String nombre) {
        conectar();
        Editorial editorial = (Editorial) em.createQuery("SELECT e FROM Editorial e WHERE e.nombre LIKE :nombre")
                .setParameter("nombre", "%"+nombre+"%").getSingleResult();
        desconectar();
        return editorial;
    }
}
