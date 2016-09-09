package com.example.alumno.appclase3;

/**
 * Created by alumno on 08/09/2016.
 */
public class Persona {

    private String nombre;
    private String apellido;
    private String celular;

    public Persona(String nom, String ape, String cel){
        this.nombre = nom;
        this.apellido = ape;
        this.celular = cel;
    }

    public String getNombre(){
         return this.nombre;
    }

    public void setNombre(String nom){
        this.nombre = nom;
    }

    public String getCelular(){
        return this.celular;
    }

    public void setCelular(String cel){
        this.apellido = cel;
    }

    public String getApellido(){
        return this.apellido;
    }

    public void setApellido(String ape){
        this.apellido = ape;
    }



}
