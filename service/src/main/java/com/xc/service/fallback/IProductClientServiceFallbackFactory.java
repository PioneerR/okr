package com.xc.service.fallback;


import com.xc.pojo.Product;
import com.xc.service.IProductClientService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IProductClientServiceFallbackFactory implements FallbackFactory<IProductClientService> {

	@Override
    public IProductClientService create(Throwable throwable) {
        return  new IProductClientService() {
            @Override
            public Product getProduct(long id) {
                Product product = new Product();
                product.setId(999999L);
                product.setName("Hystrix - 服务熔断");
                product.setDescr("Hystrix - 熔断机制");
                return  product;
            }

            @Override
            public List<Product> listProduct() {
                return null;
            }

            @Override
            public boolean addPorduct(Product product) {
                return false;
            }
        };
    }
}