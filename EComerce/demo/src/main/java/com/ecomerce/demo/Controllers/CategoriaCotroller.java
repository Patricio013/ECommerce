package com.ecomerce.demo.Controllers;

import org.springframework.data.domain.Page;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecomerce.demo.Services.CategoriasService;
import com.ecomerce.demo.Clases.Categorias;
import com.ecomerce.demo.Exceptions.CategoriasDuplicateException;
import com.ecomerce.demo.Request.CategoriaRequest;

@RestController
@RequestMapping("categorias")
public class CategoriaCotroller {
    @Autowired
    private CategoriasService categoriasService;

    @GetMapping
    public ResponseEntity<Page<Categorias>> getCategorias(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        if (page == null || size == null)
            return ResponseEntity.ok(categoriasService.getCategorias(PageRequest.of(0, Integer.MAX_VALUE)));
        return ResponseEntity.ok(categoriasService.getCategorias(PageRequest.of(page, size)));
    }

    @GetMapping("/filtro")
    public List<Categorias> Filtro (@RequestParam List<String> nombres){
        return categoriasService.filtrado(nombres);
    }
    
    @PostMapping
    public ResponseEntity<Object> createCategory(@RequestBody CategoriaRequest categoriaRequest)
            throws CategoriasDuplicateException {
        Categorias result = categoriasService.createCategoria(categoriaRequest.getNombre());
        return ResponseEntity.created(URI.create("/categories/" + result.getId())).body(result);
    }
}
