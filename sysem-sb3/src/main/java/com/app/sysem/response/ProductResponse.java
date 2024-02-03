package com.app.sysem.response;

import java.util.List;

import com.app.sysem.model.Product;

import lombok.Data;

@Data
public class ProductResponse {

	List<Product> products;

}
