package com.ecomerce.demo.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoResponse {
    
    private long id;
    private String titulo;
    private Double precio;
    private String descripcion;
    private int stock;
    private Double descuento;
    private Boolean estadoDescuento;
    private byte[] imagenes; 

}
