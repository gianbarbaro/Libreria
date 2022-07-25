/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreriajpa.persistencia;

import java.util.List;
import libreriajpa.entidades.Prestamo;

/**
 *
 * @author Usuario
 */
public final class PrestamoDAO extends DAO{
    
    public void guardar(Prestamo objeto) {
        conectar();
        em.getTransaction().begin();
        em.persist(objeto);
        em.getTransaction().commit();
        desconectar();
    }
    
    public Prestamo editar(Prestamo objeto) {
        conectar();
        em.getTransaction().begin();
        Prestamo p = em.merge(objeto);
        em.getTransaction().commit();
        desconectar();
        return p;
    }
    
    public void eliminar(Prestamo objeto) {
        conectar();
        em.getTransaction().begin();
        em.remove(objeto);
        em.getTransaction().commit();
        desconectar();
    }
    
    public Prestamo buscarPorCliente(String id) {
        conectar();
        Prestamo prestamo = (Prestamo) em.createQuery("SELECT p FROM Prestamo p WHERE p.cliente.id LIKE :id")
                .setParameter("id","%"+id+"%").getSingleResult();
        desconectar();
        return prestamo;
    }
    
    public List<Prestamo> listarTodos() {
        conectar();
        List<Prestamo> prestamos = em.createQuery("SELECT p FROM Prestamo p").getResultList();
        desconectar();
        return prestamos;
    }
}
