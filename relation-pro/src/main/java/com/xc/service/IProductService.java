package com.xc.service;


import com.xc.pojo.Product;

import java.util.List;

public interface IProductService {
    Product get(long id);
    boolean add(Product product);
    List<Product> list();
}