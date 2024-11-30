package com.nimap.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.nimap.demo.model.Category;
import com.nimap.demo.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryrepository;
	
	public Page<Category> getAll(int page, int size) {
		int zeroBasedPage = page - 1;
		return categoryrepository.findAll(PageRequest.of(zeroBasedPage, size));
	}
	
	public Optional<Category> getbyId(long id){
		return categoryrepository.findById(id);
	}
	
	public Category create(Category category) {
		return categoryrepository.save(category);
	}
	
	public Category update(Long id, Category categoryDetails) {
        return categoryrepository.findById(id).map(category -> {
            category.setName(categoryDetails.getName());
            category.setDescription(categoryDetails.getDescription());
            return categoryrepository.save(category);
        }).orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
    }
	
	public void deletebyId(long id) {
		categoryrepository.deleteById(id);
	}
}
