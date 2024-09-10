package com.ecomerce.demo.Services;

import java.util.List;
import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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

    public Page<Categorias> filtrado(List<String> nombres, PageRequest pageable) {
    List<Categorias> cat = new ArrayList<>();  
    for (String nombre : nombres) {
        List<Categorias> categoria = categoriasRepository.findByNombre(nombre);  
        if (categoria != null) {  
            cat.addAll(categoria);
        }
    }
    int start = (int) pageable.getOffset();
    int end = Math.min((start + pageable.getPageSize()), cat.size());
    return new PageImpl<>(cat.subList(start, end), pageable, cat.size());
    }


    public Categorias createCategoria(String nombre) throws CategoriasDuplicateException{
        List<Categorias> categorias = categoriasRepository.findByNombre(nombre);
        if (categorias.isEmpty())
            return categoriasRepository.save(new Categorias(nombre));
        throw new CategoriasDuplicateException();
    }
}