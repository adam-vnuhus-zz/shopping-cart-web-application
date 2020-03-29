package com.example.shoppingcart.repository;

import com.example.shoppingcart.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("SELECT o FROM Order o " +
            "WHERE (:guid IS NULL OR (o.guid = :guid))" +
            "AND (:username IS NULL OR (o.username = :username))")
    List<Order> findOrderByGuidOrUsername(@Param("guid") String guid, @Param("username") String username);
}
