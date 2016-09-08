package com.example.alumno.appclase3;

/**
 * Created by alumno on 08/09/2016.
 */
public class Persona {

    private String nombre;
    private String apellido;

    public Persona(String nom, String ape){
        this.nombre = nom;
        this.apellido = ape;
    }

    public String getNombre(){
         return this.nombre;
    }

    public void setNombre(String nom){
        this.nombre = nom;
    }

    public String getApellido(){
        return this.apellido;
    }

    public void setApellido(String ape){
        this.apellido = ape;
    }


}
