package com.aillusions.dictionary.service;

import java.io.Serializable;
import java.util.List;

import com.aillusions.dictionary.domain.Product;


public interface ProductManager extends Serializable{

    public void increasePrice(int percentage);
    
    public List<Product> getProducts();
    
}