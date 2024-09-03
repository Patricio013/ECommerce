package com.ecomerce.demo.Clases;

import lombok.Data;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Data
@Entity
public class Categorias {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombre;

    @ManyToMany(mappedBy = "categorias")
    private Set<Catalogo> catalogo;

    public Categorias(){
        
    }

    public Categorias (String nombre){
        this.nombre = nombre;
    }
}
