package com.app.sysem.services;

import org.springframework.http.ResponseEntity;

import com.app.sysem.model.Product;
import com.app.sysem.response.ProductResponseRest;

public interface IProductService {

	public ResponseEntity<ProductResponseRest> save(Product product, Long categoryId);

	public ResponseEntity<ProductResponseRest> searchById(Long id);

	public ResponseEntity<ProductResponseRest> searchByName(String name);

	public ResponseEntity<ProductResponseRest> deleteById(Long id);

	public ResponseEntity<ProductResponseRest> search();

	public ResponseEntity<ProductResponseRest> update(Product product, Long categoryId, Long id);

}
