/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreriajpa.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "Libros")
public class Libro implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    private String id;
    @Column(unique = true)
    private Long isbn;
    @Column(name = "titulo", unique = true)
    private String titulo;
    @Column(name = "año")
    private Integer anio;
    @Column(name = "ejemplares_disponibles")
    private Integer ejemplares;
    @Column(name = "ejemplares_prestados")
    private Integer ejemplaresPrestados;
    @Column(name = "ejmplares_restantes")
    private Integer ejemplaresRestantes;
    private Boolean alta;
    @ManyToOne
    @JoinColumn(name = "autor")
    private Autor autor;
    @ManyToOne
    @JoinColumn(name = "editorial")
    private Editorial editorial;

    public Libro(String id, Long isbn, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados, Integer ejemplaresRestantes, Boolean alta, Autor autor, Editorial editorial) {
        this.id = id;
        this.isbn = isbn;
        this.titulo = titulo;
        this.anio = anio;
        this.ejemplares = ejemplares;
        this.ejemplaresPrestados = ejemplaresPrestados;
        this.ejemplaresRestantes = ejemplaresRestantes;
        this.alta = alta;
        this.autor = autor;
        this.editorial = editorial;
    }

    public Libro() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getEjemplares() {
        return ejemplares;
    }

    public void setEjemplares(Integer ejemplares) {
        this.ejemplares = ejemplares;
    }

    public Integer getEjemplaresPrestados() {
        return ejemplaresPrestados;
    }

    public void setEjemplaresPrestados(Integer ejemplaresPrestados) {
        this.ejemplaresPrestados = ejemplaresPrestados;
    }

    public Integer getEjemplaresRestantes() {
        return ejemplaresRestantes;
    }

    public void setEjemplaresRestantes(Integer ejemplaresRestantes) {
        this.ejemplaresRestantes = ejemplaresRestantes;
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    @Override
    public String toString() {
        return "Libro{" + id + ", ISBN: " + isbn + ", '" + titulo + "', Año:" + anio + "\n"
                + "Autor: " + autor + ", Editorial: " + editorial + ", Alta: " + alta + "\n"
                + "Ejemplares: " + ejemplares + ", Prestados: " + ejemplaresPrestados + ", Restantes: " + ejemplaresRestantes +'}';
    }
    
}
