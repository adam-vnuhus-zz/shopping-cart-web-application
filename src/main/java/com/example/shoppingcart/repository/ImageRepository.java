package com.example.shoppingcart.repository;

import com.example.shoppingcart.entity.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Images, Integer> {
}
