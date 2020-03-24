package com.example.usermanagementrestapi.repository;

import com.example.usermanagementrestapi.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Category findAllByName(String name);

    @Query("SELECT c FROM Category c " +
            "WHERE (:categoryName IS NULL OR UPPER(c.brand) LIKE CONCAT('%',UPPER(:categoryName),'%'))")
    Page<Category> getListCategoryByCategoryNameContaining(Pageable pageable, @Param("categoryName") String categoryName);
}
