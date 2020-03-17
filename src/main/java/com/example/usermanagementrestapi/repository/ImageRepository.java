package com.example.usermanagementrestapi.repository;

import com.example.usermanagementrestapi.entity.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Images, Integer> {
}
