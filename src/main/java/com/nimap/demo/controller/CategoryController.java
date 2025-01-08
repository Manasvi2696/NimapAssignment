package com.nimap.demo.controller;

import java.util.List;
import java.util.Optional;

import com.nimap.demo.exception.CategoryNotFoundException;
import com.nimap.demo.exception.ProductNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nimap.demo.model.Category;
import com.nimap.demo.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public Page<Category> getAll(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size){
		return categoryService.getAll(page,size);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable long id){
		Optional<Category> category = categoryService.getbyId(id);
		if(category.isPresent()) {
			return ResponseEntity.ok(category.get());
		}else {
			throw new CategoryNotFoundException("Category with id : "+id+" is not found");
			//return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	public Category createCategory(@RequestBody Category category) {
		return categoryService.create(category);
	}
	
	@PutMapping("/{id}")
	public Category updateCategory(@PathVariable Long id, @RequestBody Category categoryDetails) {
		return categoryService.update(id, categoryDetails);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCategoryById(@PathVariable long id){
		Optional<Category> category = categoryService.getbyId(id);
		if(category.isPresent()) {
			categoryService.deletebyId(id);
			return ResponseEntity.noContent().build();
		}else {
			throw new CategoryNotFoundException("Category with id : "+id+" is not found");
			//return ResponseEntity.notFound().build();
		}
	}
}
