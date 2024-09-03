package com.ecomerce.demo.Repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecomerce.demo.Clases.Categorias;

@Repository
public interface CategoriasRepository extends JpaRepository<Categorias, Long>{

    @Query(value = "select c from Categorias c where c.nombre = ?1")
    List<Categorias> findByNombre(String nombre);

}
