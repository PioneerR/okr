package com.xc.service;

import com.xc.config.FeignClientConfig;
import com.xc.pojo.Product;
import com.xc.service.fallback.IProductClientServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "RELATION-PRO",configuration = FeignClientConfig.class,
				fallbackFactory = IProductClientServiceFallbackFactory.class)
public interface IProductClientService {
    @RequestMapping("/product/get/{id}")
    public Product getProduct(@PathVariable("id") long id);

    @RequestMapping("/product/list")
    public List<Product> listProduct() ;

    @RequestMapping("/product/add")
    public boolean addPorduct(Product product) ;

}