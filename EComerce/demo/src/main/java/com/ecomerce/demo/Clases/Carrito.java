package com.ecomerce.demo.Clases;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
public class Carrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private List<CatalogoItem> productos;

    private Double precioTotal;

    @OneToOne(mappedBy = "carrito")
    private Usuarios usuario;
    
}
