package com.xc.pojo;
import lombok.Data;

import java.io.Serializable;

@Data
public class Product implements Serializable {

    private Long id;
    private String name;
    private String descr;

	@Override
    public String toString() {
        return "Product{" +
                "productId=" + id +
                ", productName='" + name + '\'' +
                ", productDesc='" + descr + '\'' +
                '}';
    }
}