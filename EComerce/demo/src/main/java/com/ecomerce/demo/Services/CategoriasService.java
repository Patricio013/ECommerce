package com.ecomerce.demo.Services;

import java.util.List;
import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ecomerce.demo.Clases.Categorias;
import com.ecomerce.demo.Exceptions.CategoriasDuplicateException;
import com.ecomerce.demo.Repositorys.CategoriasRepository;

@Service
public class CategoriasService {
    
    @Autowired
    private CategoriasRepository categoriasRepository;

    public Page<Categorias> getCategorias (PageRequest pageble){
        return categoriasRepository.findAll(pageble);
    }

    public List<Categorias> filtrado(List<String> nombres){
        List<Categorias> cat = new ArrayList<>();  
        for (int i = 0; i < nombres.size(); i++) {
            List<Categorias> categoria = categoriasRepository.findByNombre(nombres.get(i));  
            if (categoria != null) {  
                cat.addAll(categoria);
            }
        }
    return cat;
    }

    public Categorias createCategoria(String nombre) throws CategoriasDuplicateException{
        List<Categorias> categorias = categoriasRepository.findByNombre(nombre);
        if (categorias.isEmpty())
            return categoriasRepository.save(new Categorias(nombre));
        throw new CategoriasDuplicateException();
    }
}