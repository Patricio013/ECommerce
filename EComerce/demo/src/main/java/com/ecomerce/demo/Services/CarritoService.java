package com.ecomerce.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomerce.demo.Clases.Carrito;
import com.ecomerce.demo.Clases.Producto;
import com.ecomerce.demo.Clases.Usuarios;
import com.ecomerce.demo.Repositorys.CarritoRepository;
import com.ecomerce.demo.Repositorys.ProductoRepository;
import com.ecomerce.demo.Repositorys.UsuariosRepository;
import com.ecomerce.demo.Response.CarritoResponse;

@Service
public class CarritoService {
    
    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired 
    private ProductoRepository productoRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UsuariosRepository usuariosRepository;

    public CarritoResponse obtenerCarrito (){
        Usuarios usuario = authenticationService.obtenerUsuarioAutenticado();
        Carrito carrito = carritoRepository.findCarrito(usuario.getId());
        if (carrito == null){
            carrito = new Carrito();
            usuario.setCarrito(carrito);
            usuariosRepository.save(usuario);
            carrito = carritoRepository.save(carrito);
        }
        CarritoResponse carritoResponse = new CarritoResponse(carrito.getProductos(), carrito.getPrecioTotal());
        return carritoResponse;
    }

    public CarritoResponse agregarProducto(long productId, int cantidad) {
        Usuarios usuario = authenticationService.obtenerUsuarioAutenticado();
        Carrito carrito = carritoRepository.findCarrito(usuario.getId());
        if (carrito == null){
            carrito = new Carrito();
            usuario.setCarrito(carrito);
            usuariosRepository.save(usuario);
            carrito = carritoRepository.save(carrito);
        }
        Producto producto = productoRepository.findById(productId);
        carrito.agregarProducto(producto, cantidad);
        carrito = carritoRepository.save(carrito);
        CarritoResponse carritoResponse = new CarritoResponse(carrito.getProductos(), carrito.getPrecioTotal());
        return carritoResponse;
    }

    public CarritoResponse quitarProducto(long productId) {
        Usuarios usuario = authenticationService.obtenerUsuarioAutenticado();
        Carrito carrito = carritoRepository.findCarrito(usuario.getId());
        Producto producto = productoRepository.findById(productId);
        carrito.quitarProducto(producto);
        carritoRepository.save(carrito);
        CarritoResponse carritoResponse = new CarritoResponse(carrito.getProductos(), carrito.getPrecioTotal());
        return carritoResponse;
    }

    public CarritoResponse vaciarCarrito() {
        Usuarios usuario = authenticationService.obtenerUsuarioAutenticado();
        Carrito carrito = carritoRepository.findCarrito(usuario.getId());
        carrito.vaciarCarrito();
        carritoRepository.save(carrito);
        CarritoResponse carritoResponse = new CarritoResponse(carrito.getProductos(), carrito.getPrecioTotal());
        return carritoResponse;
    }

    public CarritoResponse modificarCantidadProducto(long productId, int nuevaCantidad){
        Usuarios usuario = authenticationService.obtenerUsuarioAutenticado();
        Carrito carrito = carritoRepository.findCarrito(usuario.getId());
        Producto producto = productoRepository.findById(productId);
        carrito.modificarCantidadProducto(producto, nuevaCantidad);
        carritoRepository.save(carrito);
        CarritoResponse carritoResponse = new CarritoResponse(carrito.getProductos(), carrito.getPrecioTotal());
        return carritoResponse;
    }
}
