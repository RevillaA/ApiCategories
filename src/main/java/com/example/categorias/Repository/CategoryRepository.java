package com.example.categorias.Repository;

import com.example.categorias.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Aquí puedes definir métodos personalizados si es necesario
    // Por ejemplo, para buscar productos por nombre:
    // List<Category> findByName(String name);
    
}
