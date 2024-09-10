package com.ecomerce.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomerce.demo.Clases.Carrito;
import com.ecomerce.demo.Clases.Producto;
import com.ecomerce.demo.Clases.Usuarios;
import com.ecomerce.demo.Repositorys.CarritoRepository;
import com.ecomerce.demo.Repositorys.ProductoRepository;

@Service
public class CarritoService {
    
    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired 
    private ProductoRepository productoRepository;

    @Autowired
    private AuthenticationService authenticationService;

    public Carrito obtenerCarrito (){
        Usuarios usuario = authenticationService.obtenerUsuarioAutenticado();
        return carritoRepository.findCarrito(usuario.getId());
    }

    public Carrito agregarProducto(long productId, int cantidad) {
        Usuarios usuario = authenticationService.obtenerUsuarioAutenticado();
        Carrito carrito = carritoRepository.findCarrito(usuario.getId());
        Producto producto = productoRepository.findById(productId);
        carrito.agregarProducto(producto, cantidad);
        carritoRepository.save(carrito);
        return carrito;
    }

    public Carrito quitarProducto(long productId) {
        Usuarios usuario = authenticationService.obtenerUsuarioAutenticado();
        Carrito carrito = carritoRepository.findCarrito(usuario.getId());
        Producto producto = productoRepository.findById(productId);
        carrito.quitarProducto(producto);
        carritoRepository.save(carrito);
        return carrito;
    }

    public Carrito vaciarCarrito() {
        Usuarios usuario = authenticationService.obtenerUsuarioAutenticado();
        Carrito carrito = carritoRepository.findCarrito(usuario.getId());
        carrito.vaciarCarrito();
        carritoRepository.save(carrito);
        return carrito;
    }

    public Carrito modificarCantidadProducto(long productId, int nuevaCantidad){
        Usuarios usuario = authenticationService.obtenerUsuarioAutenticado();
        Carrito carrito = carritoRepository.findCarrito(usuario.getId());
        Producto producto = productoRepository.findById(productId);
        carrito.modificarCantidadProducto(producto, nuevaCantidad);
        return carritoRepository.save(carrito);
    }
}
