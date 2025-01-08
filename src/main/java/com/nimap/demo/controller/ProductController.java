package com.nimap.demo.controller;

import java.util.List;
import java.util.Optional;

import com.nimap.demo.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nimap.demo.model.Product;
import com.nimap.demo.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
	private ProductService productservice;
	
	@GetMapping
	public Page<Product> getProducts(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size){
		return productservice.getAll(page, size);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable long id){
		Optional<Product> product = productservice.getbyId(id);
		if(product.isPresent()) {
			return ResponseEntity.ok(product.get());
		}else {
			throw new ProductNotFoundException("Product with id : "+id+" is not found");
			//return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	public Product createProduct(@RequestBody Product product) {
		return productservice.create(product);
	}
	
	@PutMapping("/{id}")
    public Product updateProduct(@PathVariable long id, //@PathVariable long categoryId,
                                  @RequestBody Product productDetails) {
        return productservice.update(id, productDetails); //categoryId
    }
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProductById(@PathVariable int id){
		Optional<Product> product = productservice.getbyId(id);
		
		if(product.isPresent()) {
			productservice.deletebyId(id);
			return ResponseEntity.noContent().build();
		}else {
			throw new ProductNotFoundException("Product with id : "+id+" is not found");
			//return ResponseEntity.notFound().build();
		}
	}

}
