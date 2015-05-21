/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Date;
import java.util.LinkedList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.Libro;

/**
 *
 * @author ander
 */
@ManagedBean(name = "libC")
@SessionScoped
public class LibrosController {

    //Objetos:
    private Libro libro;
    private LinkedList<Libro> listaLibros;

    //Bandera para la operación:
    private char ope = 'A';

    /**
     * Creates a new instance of LibrosController
     */
    public LibrosController() {
        listaLibros = new LinkedList<>();

        //Crear 2 objetos
        libro = new Libro(1234, "50 SOMBRAS DE GREYS", "GIZY", Date.valueOf("2010-04-23"));
        listaLibros.add(libro);

        libro = new Libro(5678, "LOS AMORES EN TIEMPO DE COLERA", "GARCÍA MARQUEZ", Date.valueOf("2008-07-15"));
        listaLibros.add(libro);

    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public LinkedList<Libro> getListaLibros() {
        return listaLibros;
    }

    public void setListaLibros(LinkedList<Libro> listaLibros) {
        this.listaLibros = listaLibros;
    }

    /*
     * PROPIEDADES Y MÉTODOS DE NAVEGACIÓN POR LAS PÁGINAS
     * E INSTANCIACIÓN DE OBJETOS:
     */
    public String doVolver() {
        return "index";
    }

    public String doNuevoLibro() {
        ope= 'A';
        //instanciar nuevo libro:
        libro = new Libro();
        return "nuevo";
    }

    public String doGuardarLibro() {
        if (ope == 'A') {
            listaLibros.add(libro);
        } else {
            editar();
        }
        return doVolver();
    }

    public String doBorrar(int id) {

        LinkedList<Libro> listaTemp = listaLibros;
        
        for (Libro n : listaLibros) {
            if (n.getIsbn() == id) {
                listaLibros.remove(n);
                break;
            }
        }
        
        return doVolver();
    }

    public String doPrepararEdicion(Libro l) {
        ope = 'M';
        libro = l;
        return "nuevo";
    }

    public void editar() {
        for (Libro n : listaLibros) {
            if (n == libro) {
                n.setIsbn(libro.getIsbn());
                n.setTitulo(libro.getTitulo());
                n.setAutor(libro.getAutor());
                n.setFecha(libro.getFecha());
            }

        }

    }

}
