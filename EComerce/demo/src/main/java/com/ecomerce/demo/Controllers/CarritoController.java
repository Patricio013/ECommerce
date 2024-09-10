package com.ecomerce.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecomerce.demo.Clases.Carrito;
import com.ecomerce.demo.Clases.Producto;
import com.ecomerce.demo.Exceptions.StockInsuficiente;
import com.ecomerce.demo.Repositorys.ProductoRepository;
import com.ecomerce.demo.Services.CarritoService;

@RestController
@RequestMapping("carrito")
public class CarritoController {
    
    @Autowired
    private CarritoService carritoService;

    @Autowired 
    private ProductoRepository productoRepository;

    @GetMapping
    public ResponseEntity<Carrito> obtenerCarrito() {
        return ResponseEntity.ok(carritoService.obtenerCarrito());
    }

    @PostMapping("/agregar")
    public ResponseEntity<Carrito> agregarProducto(@PathVariable long productId, @PathVariable int cantidad) throws StockInsuficiente{
        Producto producto = productoRepository.findById(productId);
        if (producto.getStock() >= cantidad) {
            return ResponseEntity.ok(carritoService.agregarProducto(productId, cantidad));
        } else {
            throw new StockInsuficiente();
        }
    }

    @DeleteMapping("/quitar")
    public ResponseEntity<Carrito> quitarProducto(@PathVariable long productId) {
        return ResponseEntity.ok(carritoService.quitarProducto(productId));
    }

    @DeleteMapping("/vaciar")
    public ResponseEntity<Carrito> vaciarCarrito() {
        return ResponseEntity.ok(carritoService.vaciarCarrito());
    }

    @PutMapping("/modificarCantidad")
    public ResponseEntity<Carrito> modificarCantidadProducto(@PathVariable long productId, @PathVariable int nuevaCantidad) throws StockInsuficiente {
        Producto producto = productoRepository.findById(productId);
        if (producto.getStock() >= nuevaCantidad) {
            return ResponseEntity.ok(carritoService.modificarCantidadProducto(productId, nuevaCantidad));
        } else {
            throw new StockInsuficiente();
        }
    }
    
}

