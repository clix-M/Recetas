package clix.model;

import javax.swing.*;

/*
* id_receta INT PRIMARY KEY AUTO_INCREMENT,
  nombre VARCHAR(255),
  descripcion TEXT,
  tiempo_de_preparacion INT,
  instruccion_de_preparacion TEXT,
  dificultad INT,
  id_categoria INT

  * */
public class ModelReceta {
    int id_receta;
    String nombre;
    String descripcion;
    double tiempo_de_preparacion;
    String instruccion_de_preparacion;
    int dificultad;
    int id_categoria;

    Icon imagen;

    public ModelReceta() {
    }

    public ModelReceta(int id_receta, String nombre, String descripcion, double tiempo_de_preparacion, String instruccion_de_preparacion, int dificultad, int id_categoria, Icon imagen) {
        this.id_receta = id_receta;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tiempo_de_preparacion = tiempo_de_preparacion;
        this.instruccion_de_preparacion = instruccion_de_preparacion;
        this.dificultad = dificultad;
        this.id_categoria = id_categoria;
        this.imagen = imagen;
    }

    public int getId_receta() {
        return id_receta;
    }

    public void setId_receta(int id_receta) {
        this.id_receta = id_receta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getTiempo_de_preparacion() {
        return tiempo_de_preparacion;
    }

    public void setTiempo_de_preparacion(double tiempo_de_preparacion) {
        this.tiempo_de_preparacion = tiempo_de_preparacion;
    }

    public String getInstruccion_de_preparacion() {
        return instruccion_de_preparacion;
    }

    public void setInstruccion_de_preparacion(String instruccion_de_preparacion) {
        this.instruccion_de_preparacion = instruccion_de_preparacion;
    }

    public int getDificultad() {
        return dificultad;
    }

    public void setDificultad(int dificultad) {
        this.dificultad = dificultad;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public Icon getImagen() {
        return imagen;
    }

    public void setImagen(Icon imagen) {
        this.imagen = imagen;
    }
}
