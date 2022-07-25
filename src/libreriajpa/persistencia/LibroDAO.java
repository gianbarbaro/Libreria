/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreriajpa.persistencia;

import java.util.List;
import libreriajpa.entidades.Libro;

/**
 *
 * @author Usuario
 */
public final class LibroDAO extends DAO {
    
    public void guardar(Libro objeto) {
        conectar();
        em.getTransaction().begin();
        em.persist(objeto);
        em.getTransaction().commit();
        desconectar();
    }
    
    public Libro editar(Libro objeto) {
        conectar();
        em.getTransaction().begin();
        Libro l = em.merge(objeto);
        em.getTransaction().commit();
        desconectar();
        return l;
    }
    
    public void eliminar(Libro objeto) {
        conectar();
        em.getTransaction().begin();
        em.remove(objeto);
        em.getTransaction().commit();
        desconectar();
    }
    
    public Libro buscarPorISBN(Long isbn) {
        conectar();
        Libro libro = (Libro) em.createQuery("SELECT l FROM Libro l WHERE l.isbn LIKE :isbn")
                .setParameter("isbn", isbn).getSingleResult();
        desconectar();
        return libro;
    }
    
    public Libro buscarPorTitulo(String titulo) {
        conectar();
        Libro libro = (Libro) em.createQuery("SELECT l FROM Libro l WHERE l.titulo LIKE :titulo")
                .setParameter("titulo","%"+titulo+"%").getSingleResult();
        desconectar();
        return libro;
    }
    
    public List<Libro> buscarPorAutor(String nombre){
        conectar();
        List<Libro> libros = em.createQuery("SELECT l FROM Libro l WHERE l.autor.nombre LIKE :nombre")
                .setParameter("nombre","%"+nombre+"%").getResultList();
        desconectar();
        return libros;
    }
    
    public List<Libro> buscarPorEditorial(String nombre){
        conectar();
        List<Libro> libros = em.createQuery("SELECT l FROM Libro l WHERE l.editorial.nombre LIKE :nombre")
                .setParameter("nombre","%"+nombre+"%").getResultList();
        desconectar();
        return libros;
    }
    
    public List<Libro> listarTodos() {
        conectar();
        List<Libro> libros = em.createQuery("SELECT e FROM Libro e").getResultList();
        desconectar();
        return libros;
    }
}
