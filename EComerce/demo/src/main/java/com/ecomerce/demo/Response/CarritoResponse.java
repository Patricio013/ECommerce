package com.ecomerce.demo.Response;

import java.util.List;

import com.ecomerce.demo.Clases.ProductoCarrito;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarritoResponse {
    private List<ProductoCarrito> productos;
    private Double precioTotal;
}
