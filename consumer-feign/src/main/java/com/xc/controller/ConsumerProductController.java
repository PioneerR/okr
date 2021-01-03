package com.xc.controller;

import com.xc.pojo.Product;
import com.xc.service.IProductClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer")
public class ConsumerProductController {

//    public static final String PRODUCT_GET_URL = "http://RELATION-PRO/product/get/";
//    public static final String PRODUCT_LIST_URL= "http://RELATION-PRO/product/list/";
//    public static final String PRODUCT_ADD_URL = "http://RELATION-PRO/product/add/";
//	public static final String PRODUCT_TOPIC = "RELATION-PRO";

//    @Resource
//    private RestTemplate restTemplate;
//    @Resource
//    private HttpHeaders httpHeaders;
//    @Resource
//    private LoadBalancerClient loadBalancerClient;

	@Autowired
	private IProductClientService service;

    @RequestMapping("/product/get")
    public Object getProduct(long id) {
        /*Product product = restTemplate.exchange(PRODUCT_GET_URL + id,
				HttpMethod.GET,new HttpEntity<>(httpHeaders), Product.class).getBody();
        return  product;*/
        return service.getProduct(id);
    }

    @RequestMapping("/product/list")
    public Object listProduct() {
		/*ServiceInstance serviceInstance = this.loadBalancerClient.choose("RELATION-PRO") ;
		System.out.println(
				"【*** ServiceInstance ***】host = " + serviceInstance.getHost()
						+ "、port = " + serviceInstance.getPort()
						+ "、serviceId = " + serviceInstance.getServiceId());

        List<Product> list = restTemplate.exchange(PRODUCT_LIST_URL, HttpMethod.GET,
                new HttpEntity<>(httpHeaders), List.class).getBody();
        return  list;*/
		return service.listProduct();
    }

    @RequestMapping("/product/add")
    public Object addPorduct(Product product) {
        /*Boolean result = restTemplate.exchange(PRODUCT_ADD_URL, HttpMethod.POST,
                new HttpEntity<Object>(product,httpHeaders), Boolean.class).getBody();
        return  result;*/
        return service.addPorduct(product);
    }
}