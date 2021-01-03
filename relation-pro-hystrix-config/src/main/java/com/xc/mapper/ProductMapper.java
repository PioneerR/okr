package com.xc.mapper;


import com.xc.pojo.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface ProductMapper {

	boolean create(Product product);

	public Product findById(Long id);

	public List<Product> findAll();
}