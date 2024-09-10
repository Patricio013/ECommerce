package com.ecomerce.demo.Repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecomerce.demo.Clases.Carrito;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long>{

    @Query("SELECT c FROM Carrito c WHERE c.usuario.id = :userId")
    Carrito findCarrito (long userId);
}
