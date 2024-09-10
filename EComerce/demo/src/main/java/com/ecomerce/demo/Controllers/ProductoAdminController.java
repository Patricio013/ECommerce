package com.ecomerce.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.ecomerce.demo.Request.ProductoRequest;
import com.ecomerce.demo.Response.ProductoResponse;
import com.ecomerce.demo.Services.ProductoService;

import java.io.IOException;

@RestController
@RequestMapping("productosAdmin")
public class ProductoAdminController {
    @Autowired
    private ProductoService productoService;

    @PostMapping
    public ResponseEntity<ProductoResponse> crearProducto(@RequestBody ProductoRequest productoRequest) {
        ProductoResponse producto = productoService.crearProducto(productoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(producto);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<ProductoResponse> actualizarProducto(@RequestParam long id, @RequestBody ProductoRequest productoRequest) {
        ProductoResponse producto = productoService.actualizarProducto(id, productoRequest);
        return ResponseEntity.ok(producto);
    }

    @DeleteMapping("/borrar")
    public ResponseEntity<Void> eliminarProducto(@RequestParam long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/subir/imagen")
    public ResponseEntity<Void> subirImagen(@RequestParam long id, @RequestParam("imagen") MultipartFile imagen) throws IOException {
        productoService.subirImagen(id, imagen.getBytes());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/borrar/imagen")
    public ResponseEntity<Void> eliminarImagen(@PathVariable long id) {
        productoService.eliminarImagen(id);
        return ResponseEntity.noContent().build();
    }
}
