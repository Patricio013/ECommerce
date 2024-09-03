package com.ecomerce.demo.Clases;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Catalogo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String titulo;
    private Double precio;
    private String descripcion;
    private int stock;

    @ManyToOne
    @JoinColumn(name = "creador_id", nullable = false)
    private Usuarios creador;

    @ManyToMany
    @JoinTable(
        name = "catalogo_categoria",
        joinColumns = @JoinColumn(name = "catalogo_id"),
        inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    private Set<Categorias> categorias;
    
}
