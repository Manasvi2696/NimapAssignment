package com.nimap.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.nimap.demo.model.Product;
import com.nimap.demo.repository.CategoryRepository;
import com.nimap.demo.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productrepository;
	@Autowired
	private CategoryRepository categoryrepository;
	
	public Page<Product> getAll(int page, int size){
		int zeroBasedPage = page - 1;
		return productrepository.findAll(PageRequest.of(zeroBasedPage, size));
	}
	
	public Optional<Product> getbyId(long id){
		return productrepository.findById(id);
	}

	public Product create(Product product) {
		return productrepository.save(product);
	}
	
	public Product update(long id, Product productDetails) { //long categoryId
        return productrepository.findById(id).map(product -> {
            product.setName(productDetails.getName());
            product.setPrice(productDetails.getPrice());
            //categoryrepository.findById(categoryId).ifPresent(product::setCategory);
            return productrepository.save(product);
        }).orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }
	
	public void deletebyId(long id) {
		productrepository.deleteById(id);
	}
}
