/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreriajpa.persistencia;

import java.util.List;
import libreriajpa.entidades.Autor;

/**
 *
 * @author Usuario
 */
public final class AutorDAO extends DAO {
    
    public void guardar(Autor objeto) {
        conectar();
        em.getTransaction().begin();
        em.persist(objeto);
        em.getTransaction().commit();
        desconectar();
    }
    
    public Autor editar(Autor objeto) {
        conectar();
        em.getTransaction().begin();
        Autor a = em.merge(objeto);
        em.getTransaction().commit();
        desconectar();
        return a;
    }
    
    public void eliminar(Autor objeto) {
        conectar();
        em.getTransaction().begin();
        em.remove(objeto);
        em.getTransaction().commit();
        desconectar();
    }
    
    public Autor buscarPorNombre(String nombre) {
        conectar();
        Autor autor = (Autor) em.createQuery("SELECT a FROM Autor a WHERE a.nombre LIKE :nombre")
                .setParameter("nombre", "%"+nombre+"%").getSingleResult();
        desconectar();
        return autor;
    }
    
    public List<Autor> listarTodos() {
        conectar();
        List<Autor> autores = em.createQuery("SELECT a FROM Autor a").getResultList();
        desconectar();
        return autores;
    }
    
    public Autor buscarPorID(String id) {
        conectar();
        Autor autor = em.find(Autor.class, id);
        desconectar();
        return autor;
    }
}
