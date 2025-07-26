package com.example.categorias.Services;

import com.example.categorias.Model.Category;
import com.example.categorias.Repository.CategoryRepository;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        return categoryRepository.findById(id)
            .map(existing -> {
                existing.setName(category.getName());
                existing.setDescription(category.getDescription());
                existing.setUpdatedAt(LocalDateTime.now());
                return categoryRepository.save(existing);
            })
            .orElseThrow(() -> new IllegalArgumentException("Category not found with id " + id));
    }

    @Override
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new IllegalArgumentException("Category not found with id " + id);
        }
        categoryRepository.deleteById(id);
    }
}
