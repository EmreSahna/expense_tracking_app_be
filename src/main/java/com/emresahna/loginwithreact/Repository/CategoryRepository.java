package com.emresahna.loginwithreact.Repository;

import com.emresahna.loginwithreact.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
