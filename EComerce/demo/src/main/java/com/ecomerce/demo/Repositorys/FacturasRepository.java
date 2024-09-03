package com.ecomerce.demo.Repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomerce.demo.Clases.Facturas;

@Repository
public interface FacturasRepository extends JpaRepository<Facturas, Long>{
    
}
