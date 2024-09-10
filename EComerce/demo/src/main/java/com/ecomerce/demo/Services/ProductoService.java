package com.ecomerce.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecomerce.demo.Clases.Producto;
import com.ecomerce.demo.Repositorys.ProductoRepository;
import com.ecomerce.demo.Request.ProductoRequest;
import com.ecomerce.demo.Response.ProductoResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<ProductoResponse> obtenerProductosPorUsuario() {
        List<Producto> productos = productoRepository.findAllWithStock();
        return productos.stream()
                .map(this::mapearAProductoResponse) 
                .collect(Collectors.toList());
    }

    public ProductoResponse obtenerProducto(long productId){
        Producto producto = productoRepository.findById(productId);
        return mapearAProductoResponse(producto);
    }

    public ProductoResponse crearProducto(ProductoRequest productoRequest) {
        Producto producto = new Producto();
        producto.setTitulo(productoRequest.getTitulo());
        producto.setPrecio(productoRequest.getPrecio());
        producto.setDescripcion(productoRequest.getDescripcion());
        producto.setStock(productoRequest.getStock());
        producto.setDescuento(productoRequest.getDescuento());
        producto.setEstadoDescuento(productoRequest.getEstadoDescuento());
        producto.setImagenes(productoRequest.getImagenes()); 

        productoRepository.save(producto);
        return mapearAProductoResponse(producto);
    }

    public ProductoResponse actualizarProducto(long id, ProductoRequest productoRequest) {
        Producto producto = productoRepository.findById(id);
        producto.setTitulo(productoRequest.getTitulo());
        producto.setPrecio(productoRequest.getPrecio());
        producto.setDescripcion(productoRequest.getDescripcion());
        producto.setStock(productoRequest.getStock());
        producto.setDescuento(productoRequest.getDescuento());
        producto.setEstadoDescuento(productoRequest.getEstadoDescuento());
        producto.setImagenes(productoRequest.getImagenes()); 
        productoRepository.save(producto);
        return mapearAProductoResponse(producto);
    }

    public void eliminarProducto(long id) {
        Producto producto = productoRepository.findById(id);
        productoRepository.delete(producto);
    }

    public void subirImagen(long id, byte[] imagenBytes) {
        Producto producto = productoRepository.findById(id);
        producto.setImagenes(imagenBytes);
        productoRepository.save(producto);
    }

    public void eliminarImagen(long id) {
        Producto producto = productoRepository.findById(id);
        producto.setImagenes(null); 
        productoRepository.save(producto);
    }

    private ProductoResponse mapearAProductoResponse(Producto producto) {
        return new ProductoResponse(
            producto.getId(),
            producto.getTitulo(),
            producto.getPrecio(),
            producto.getDescripcion(),
            producto.getStock(),
            producto.getDescuento(),
            producto.getEstadoDescuento(),
            producto.getImagenes()
        );
    }

}