package com.ecomerce.demo.Clases;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class CatalogoItem {
    private Long id;
    private int stock;
}
